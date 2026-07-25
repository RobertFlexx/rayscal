# Building rayscal

This document covers how to build rayscal from source, use it as an sbt
dependency, and produce native binaries.

## Prerequisites

- JDK 17+ (Temurin 21 is what CI uses)
- sbt 1.12.14
- Clang / LLVM **16+** recommended (Scala Native 0.5.12 deprecates older toolchains)
- raylib 6.0 installed as a system library
- pkg-config
- Standard Linux dev headers: X11, OpenGL, ALSA

On Ubuntu/Debian:

```bash
sudo apt update
sudo apt install -y \
  build-essential clang lld cmake git pkg-config \
  libasound2-dev libgl1-mesa-dev libglu1-mesa-dev \
  libx11-dev libxcursor-dev libxi-dev libxinerama-dev libxrandr-dev
```

Prefer Clang 16 or newer (`clang --version`). On older distros, install a newer
LLVM toolchain from your distribution's packages if the default Clang is below 16.

On Fedora:

```bash
sudo dnf install -y \
  gcc clang cmake git pkg-config \
  alsa-lib-devel mesa-libGL-devel mesa-libGLU-devel \
  libX11-devel libXcursor-devel libXi-devel libXinerama-devel libXrandr-devel
```

## Installing raylib 6.0

rayscal targets raylib 6.0 specifically. The build enforces this via
`pkg-config --modversion raylib`.

### From source (recommended for getting 6.0)

```bash
git clone --depth 1 --branch 6.0 https://github.com/raysan5/raylib.git /tmp/raylib
cmake -B /tmp/raylib/build -S /tmp/raylib -DBUILD_SHARED_LIBS=ON -DBUILD_EXAMPLES=OFF
cmake --build /tmp/raylib/build --parallel $(nproc)
sudo cmake --install /tmp/raylib/build
sudo ldconfig
```

### From a package manager

Some distros ship raylib 6.0 or close to it. Check with:

```bash
pkg-config --modversion raylib
```

If you get `6.0` or `6.0.0`, you're good. If your distro only has 5.x, build
from source.

## Building rayscal itself

```bash
git clone https://github.com/RobertFlexx/rayscal.git
cd rayscal
```

Compile the core module:

```bash
sbt core/compile
```

Run the headless FFI / ownership checks:

```bash
sbt ffiSafety/run
```

Run the graphical ABI smoke test (needs a display; on headless machines use
`xvfb-run -a sbt abiCheck/run`):

```bash
sbt abiCheck/run
```

Build and run any example:

```bash
sbt helloWindow/run
sbt starRescue/run
```

Build all examples at once:

```bash
sbt check
```

This compiles core, runs `ffiSafety` and `abiCheck`, and links every example
binary. CI runs the same command under Xvfb.

## Using rayscal from another sbt project

rayscal is not published to Maven Central yet. Use `publishLocal` to make it
available on your machine, then reference it as a library dependency.

### Step 1: Publish locally

From the rayscal repository:

```bash
sbt core/publishLocal
```

This publishes `io.github.rayscal:rayscal-core_native0.5_3:0.2.1` to your local
Ivy/Coursier cache (Scala Native cross version included in the artifact name).

### Step 2: Set up your project

Your project needs the Scala Native sbt plugin and must link against raylib.

**project/build.properties:**

```properties
sbt.version=1.12.14
```

**project/plugins.sbt:**

```scala
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.5.12")
```

**build.sbt:**

```scala
import scala.scalanative.build.*
import scala.sys.process.*
import scala.util.Try

def raylibLinkOptions: Seq[String] =
  Try("pkg-config --libs raylib".!!.trim)
    .toOption
    .filter(_.nonEmpty)
    .map(_.split("\\s+").toSeq)
    .getOrElse(Seq("-lraylib"))

def raylibCompileOptions: Seq[String] =
  Try("pkg-config --cflags raylib".!!.trim)
    .toOption
    .filter(_.nonEmpty)
    .map(_.split("\\s+").toSeq)
    .getOrElse(Seq.empty)

ThisBuild / scalaVersion := "3.8.4"

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaNativePlugin)
  .settings(
    name := "my-game",
    libraryDependencies += "io.github.rayscal" %%% "rayscal-core" % "0.2.1",
    nativeConfig ~= { c =>
      c.withLinkingOptions(c.linkingOptions ++ raylibLinkOptions)
       .withCompileOptions(c.compileOptions ++ raylibCompileOptions)
    }
  )
```

Use `%%%` so sbt resolves the Scala Native artifact. rayscal is a binding, not a
bundled copy of raylib. Your binary links against the system `libraylib.so` at
link time and loads it at runtime. `pkg-config` picks up the right flags
automatically when raylib was installed normally.

### Step 3: Build and run

```bash
sbt run           # compile and launch (from your project's root)
sbt nativeLink    # compile to a native binary under target/scala-<version>/
```

rayscal produces a standalone native executable but the system raylib shared
library must be available at runtime unless the application is explicitly
configured for static linking. The binary does not need sbt, a JVM, or Scala
installed on the target machine.

## Producing a distributable binary

```bash
sbt nativeLink
```

The output binary lands under `target/scala-<version>/` (named after your sbt
project). It's a regular ELF binary. Copy it to a Linux machine that has raylib
6.0 available at runtime and run it directly.

If you want a static binary, you'll need a static build of raylib (`-DBUILD_SHARED_LIBS=OFF`)
and may need to adjust linker flags to include all transitive dependencies
(X11, GL, ALSA, etc.).

## CI

The GitHub Actions workflow (`.github/workflows/ci.yml`) builds raylib 6.0 from
source, then runs `xvfb-run -a sbt check` on every push and pull request. A
separate release workflow builds and uploads example binaries when a `v*` tag is
pushed.

## Troubleshooting

### `ld: cannot find -lraylib`

raylib is not installed or pkg-config can't find it. Verify:

```bash
pkg-config --libs raylib
ldconfig -p | grep raylib
```

If raylib is in a non-standard location:

```bash
export PKG_CONFIG_PATH=/opt/raylib/lib/pkgconfig:$PKG_CONFIG_PATH
```

### `raylib.h is not found`

The C shim compilation step needs raylib headers. Same fix as above -- make sure
`pkg-config --cflags raylib` works.

### `libraylib.so: cannot open shared object file`

The binary linked fine but the dynamic linker can't find raylib at runtime.
Either run `sudo ldconfig` or:

```bash
export LD_LIBRARY_PATH=/usr/local/lib:$LD_LIBRARY_PATH
```

### ELF interpreter mismatch

If `sbt run` fails with "No such file or directory" right after linking, the
binary's ELF interpreter may not match your system. The build script detects it
from `/bin/sh`, but if you changed toolchains, relink:

```bash
sbt clean nativeLink
```
