# rayscal

> A tiny rascal of a Scala Native binding for raylib.

>***Very Experimental, expect bugs. But it will get better im trying my best.***

[![Status](https://img.shields.io/badge/status-experimental%20pre--alpha-orange.svg)](#project-status)
[![Scala](https://img.shields.io/badge/Scala-3-red.svg)](https://www.scala-lang.org/)
[![Scala Native](https://img.shields.io/badge/Scala%20Native-0.5.x-blue.svg)](https://scala-native.org/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

`rayscal` is a small, direct Scala Native binding for
[raylib](https://www.raylib.com/). It starts close to raylib's C API so the
behavior stays predictable, while leaving room for a friendlier Scala layer as
the binding matures.

The project is Linux-first for now and assumes raylib is installed on the host
system.

## Quick Start

If you installed Scala, sbt, and raylib with Linuxbrew/Homebrew, this is the
exact command used to validate this repository:

```bash
PATH=/home/linuxbrew/.linuxbrew/bin:$PATH sbt "all core/compile helloWindow/nativeLink bouncingBall/nativeLink keyboardInput/nativeLink rlglTriangle/nativeLink shapesGallery/nativeLink textureChecker/nativeLink basic3d/nativeLink camera2d/nativeLink renderTexture/nativeLink"
```

Run the smallest example:

```bash
PATH=/home/linuxbrew/.linuxbrew/bin:$PATH sbt helloWindow/run
```

If `sbt` is already on your `PATH`, the shorter commands work:

```bash
sbt "all core/compile helloWindow/nativeLink bouncingBall/nativeLink keyboardInput/nativeLink rlglTriangle/nativeLink shapesGallery/nativeLink textureChecker/nativeLink basic3d/nativeLink camera2d/nativeLink renderTexture/nativeLink"
sbt helloWindow/run
```

## Project Status

Experimental pre-alpha.

The current binding is intentionally small. It supports enough of raylib core to
open a window, draw text, clear the background, draw circles, read keyboard
and mouse input, draw common 2D shapes, generate/load/draw textures, call an
initial subset of `rlgl`, draw basic 3D shapes with cameras, bind the audio
device/sound/music lifecycle, support render textures and shader lifecycle
helpers, and run the starter examples. It does not claim complete raylib support
yet.

## Requirements

- JDK 17 or newer
- sbt 1.x
- Scala Native toolchain dependencies
- raylib installed as a system library
- Linux with a working graphics stack

Scala Native also needs native build tools such as Clang/LLVM and a linker.

## Installing raylib on Linux

### Ubuntu or Debian

Some distributions package raylib:

```bash
sudo apt update
sudo apt install libraylib-dev
```

If your distribution does not provide `libraylib-dev`, build raylib from source:

```bash
sudo apt update
sudo apt install build-essential git cmake libasound2-dev libx11-dev libxrandr-dev libxi-dev libgl1-mesa-dev libglu1-mesa-dev libxcursor-dev libxinerama-dev
git clone https://github.com/raysan5/raylib.git
cd raylib
cmake -B build -S . -DBUILD_SHARED_LIBS=ON
cmake --build build
sudo cmake --install build
sudo ldconfig
```

### Fedora

```bash
sudo dnf install raylib-devel
```

Package names vary by distro and release. If a package is unavailable, use the
source build above.

## Repository Layout

```text
rayscal/
  README.md
  CONTRIBUTING.md
  LICENSE
  .gitignore
  build.sbt
  project/
    build.properties
    plugins.sbt
  modules/
    core/
      src/main/scala/rayscal/
        Colors.scala
        Audio.scala
        Camera.scala
        Collisions.scala
        Vector.scala
        Window.scala
        Drawing.scala
        Input.scala
        Rlgl.scala
        Rect.scala
        Shapes.scala
        RenderTargets.scala
        ScreenSpace.scala
        Shaders.scala
        Textures.scala
        Time.scala
        Utils.scala
        raw/
          package.scala
          Raylib.scala
          Rlgl.scala
          RayscalNative.scala
      src/main/resources/
        scala-native/
          rayscal.c
  examples/
    hello-window/
      src/main/scala/
        Main.scala
    bouncing-ball/
      src/main/scala/
        Main.scala
    keyboard-input/
      src/main/scala/
        Main.scala
    rlgl-triangle/
      src/main/scala/
        Main.scala
    shapes-gallery/
      src/main/scala/
        Main.scala
    texture-checker/
      src/main/scala/
        Main.scala
    basic-3d/
      src/main/scala/
        Main.scala
    camera-2d/
      src/main/scala/
        Main.scala
    render-texture/
      src/main/scala/
        Main.scala
```

## Architecture

`rayscal` has two layers today:

- `rayscal.raw.Raylib`: raw Scala Native `@extern` bindings. Names intentionally
  match raylib's C functions, such as `InitWindow`, `BeginDrawing`, and
  `DrawText`.
- `rayscal.raw.Rlgl`: raw Scala Native `@extern` bindings for raylib's `rlgl`
  module. Names intentionally match C functions, such as `rlBegin`,
  `rlVertex3f`, and `rlSetBlendMode`.
- `rayscal.raw.RayscalNative`: tiny project-owned native shims for C ABI edges
  where a direct extern would be less reliable. Keep this file small and
  boring.
- `rayscal.raw.Color` and `rayscal.raw.Vector2`: C-shaped type aliases used by
  the extern layer.
- Friendly helpers in `rayscal`: `Window`, `Drawing`, `Colors`, `Vector`,
  `Rect`, `Shapes`, `Keyboard`, `Mouse`, `Keys`, `Time`, `Images`, `Textures`,
  `Cameras`, `Shapes3D`, `Collisions`, `Audio`, `Sounds`, `MusicStreams`, and
  `RenderTargets`, `Shaders`, `ScreenSpace`, and `Rlgl` reduce repetitive
  `Zone`, `CString`, C struct, resource lifecycle, and integer enum setup while
  staying thin.

The raw layer should remain boring and close to C. The friendly layer should
make Scala examples pleasant without blocking direct access to raylib as more of
the API is bound.

Some raylib structs contain nested structs and are passed or returned by value.
Those aliases are flattened in `rayscal.raw.package` to match the C memory
layout directly. For larger returned structs, such as `RenderTexture2D`,
`rayscal` uses a tiny C shim that writes into caller-owned memory. This keeps
the public Scala API simple while avoiding fragile native ABI lowering.

The intended long-term shape is:

- `rayscal.raw`: broad, low-level C interop for raylib, raymath, rlgl, and
  related structs/enums.
- `rayscal`: small Scala wrappers that make common use pleasant while keeping
  performance and behavior predictable.
- `examples`: focused programs that prove each newly bound area works in a real
  native binary.

## Bound API Surface

Current core functions:

- `InitWindow`
- `CloseWindow`
- `WindowShouldClose`
- window state and sizing helpers such as `IsWindowReady`, `IsWindowFocused`,
  `SetWindowTitle`, `SetWindowSize`, `ToggleFullscreen`, and related functions
- `BeginDrawing`
- `EndDrawing`
- `ClearBackground`
- `BeginMode2D`, `EndMode2D`, `BeginMode3D`, `EndMode3D`
- blend/scissor helpers: `BeginBlendMode`, `EndBlendMode`, `BeginScissorMode`,
  `EndScissorMode`
- `DrawText`
- `DrawFPS`, `MeasureText`, `SetTextLineSpacing`
- `DrawCircle`
- `DrawCircleV`
- common 2D shapes: pixels, lines, circles, ellipses, rings, rectangles,
  triangles, and polygons
- `SetTargetFPS`
- `GetFrameTime`
- `GetTime`
- `GetFPS`
- `GetScreenWidth`
- `GetScreenHeight`
- `GetRenderWidth`
- `GetRenderHeight`
- random/screenshot/config/log helpers
- `IsKeyDown`
- `IsKeyPressed`
- `IsKeyPressedRepeat`
- `IsKeyReleased`
- `IsKeyUp`
- queued key/char input
- mouse buttons, position, delta, wheel, cursor visibility/locking
- images and textures: `LoadImage`, `GenImageColor`, `GenImageChecked`,
  `UnloadImage`, `LoadTexture`, `LoadTextureFromImage`, `UnloadTexture`,
  `DrawTexture*`, texture filter/wrap
- render texture lifecycle and draw-to-texture mode
- shader lifecycle, shader mode, shader locations, texture uniforms
- screen/world coordinate conversion helpers for 2D and 3D cameras
- color utility functions: fade, tint, brightness, HSV conversion
- basic 2D collision helpers
- basic 3D drawing: lines, points, circles, triangles, cubes, spheres,
  cylinders, capsules, planes, rays, grids
- basic 3D collision helpers for spheres, boxes, and rays
- audio device, wave, sound, and music stream lifecycle/playback helpers

Current `rlgl` functions:

- matrix stack and transforms: `rlMatrixMode`, `rlPushMatrix`, `rlPopMatrix`,
  `rlLoadIdentity`, `rlTranslatef`, `rlRotatef`, `rlScalef`, `rlSetClipPlanes`
- immediate drawing: `rlBegin`, `rlEnd`, `rlVertex2i`, `rlVertex2f`,
  `rlVertex3f`, `rlTexCoord2f`, `rlNormal3f`, `rlColor4ub`, `rlColor3f`,
  `rlColor4f`
- render state: texture, cubemap, shader, framebuffer, blend, depth, culling,
  scissor, wire/point mode, line width, smooth lines, stereo render
- simple helpers: `rlSetTexture`, `rlLoadDrawCube`, `rlLoadDrawQuad`
- GPU resource foundations: vertex arrays/buffers, framebuffer load/attach,
  shader program compile/load/unload, compute dispatch, SSBO bind/unload

Current basic types:

- `Color`
- `Vector2`
- `Vector3`
- `Vector4`
- `Quaternion`
- `Matrix`
- `Rectangle`
- `Image`
- `Texture2D`
- `RenderTexture2D`
- `Shader`
- `Camera2D`
- `Camera3D`
- `Ray`
- `BoundingBox`
- `RayCollision`
- `Wave`
- `Sound`
- `Music`

Current color helpers:

- `RAYWHITE`
- `BLACK`
- `WHITE`
- `RED`
- `GREEN`
- `BLUE`
- additional raylib palette colors including `SKYBLUE`, `DARKGRAY`, `YELLOW`,
  `ORANGE`, `PURPLE`, `BROWN`, `BLANK`, and more

## Build

Compile the core module:

```bash
sbt core/compile
```

Compile all native binaries:

```bash
sbt "all core/compile helloWindow/nativeLink bouncingBall/nativeLink keyboardInput/nativeLink rlglTriangle/nativeLink shapesGallery/nativeLink textureChecker/nativeLink basic3d/nativeLink camera2d/nativeLink renderTexture/nativeLink"
```

## Run Examples

Hello window:

```bash
sbt helloWindow/run
```

Bouncing ball:

```bash
sbt bouncingBall/run
```

Keyboard input:

```bash
sbt keyboardInput/run
```

rlgl triangle:

```bash
sbt rlglTriangle/run
```

Shapes and mouse input:

```bash
sbt shapesGallery/run
```

Generated texture:

```bash
sbt textureChecker/run
```

Basic 3D:

```bash
sbt basic3d/run
```

2D camera:

```bash
sbt camera2d/run
```

Render texture:

```bash
sbt renderTexture/run
```

The hello-window example opens an 800x450 window and draws:

```text
Hello from rayscal!
```

## Linker and Library Path Troubleshooting

### Homebrew/Linuxbrew `sbt`, `java`, or `brew` is not found

If you installed Scala and sbt through Homebrew on Linux, non-interactive shells
may not include Homebrew's bin directory.

Check:

```bash
command -v brew
command -v java
command -v sbt
```

For Linuxbrew, load Homebrew's shell environment:

```bash
eval "$(/home/linuxbrew/.linuxbrew/bin/brew shellenv)"
```

Or prefix a command directly:

```bash
PATH=/home/linuxbrew/.linuxbrew/bin:$PATH sbt helloWindow/run
```

For Apple Silicon macOS, the Homebrew prefix is usually `/opt/homebrew`:

```bash
eval "$(/opt/homebrew/bin/brew shellenv)"
```

This project is still Linux-first, but the same PATH issue can appear on macOS
when tools were installed with Homebrew.

### `ld: cannot find -lraylib`

The linker cannot find `libraylib.so` or `libraylib.a`.

Check whether raylib is installed:

```bash
pkg-config --libs raylib
ldconfig -p | grep raylib
```

`build.sbt` uses `pkg-config --libs raylib` when available. If `pkg-config`
prints the correct `-L... -lraylib` flags, sbt should pick them up.

If raylib is installed in a custom prefix and `pkg-config` is unavailable, add a
linker path in `build.sbt`:

```scala
nativeConfig ~= { config =>
  config.withLinkingOptions(config.linkingOptions ++ Seq("-L/opt/raylib/lib", "-lraylib"))
}
```

You may also need:

```bash
export LD_LIBRARY_PATH=/opt/raylib/lib:$LD_LIBRARY_PATH
```

### `raylib.h` is not found while compiling native code

`rayscal` includes a tiny C shim for a small number of ABI-sensitive raylib
calls. That shim needs raylib's C include path.

Check:

```bash
pkg-config --cflags raylib
```

`build.sbt` uses `pkg-config --cflags raylib` automatically. If raylib is
installed in a custom prefix, make sure its `.pc` file is visible:

```bash
export PKG_CONFIG_PATH=/opt/raylib/lib/pkgconfig:$PKG_CONFIG_PATH
```

### Runtime error: `libraylib.so: cannot open shared object file`

The binary linked successfully, but the dynamic loader cannot find raylib at
runtime.

`build.sbt` embeds an rpath for any `-L...` path reported by
`pkg-config --libs raylib`. If you installed raylib after linking, or changed
where raylib is installed, relink the example:

```bash
sbt camera2d/nativeLink
sbt camera2d/run
```

Run:

```bash
sudo ldconfig
```

Or set:

```bash
export LD_LIBRARY_PATH=/usr/local/lib:$LD_LIBRARY_PATH
```

### `Exec failed, error: 2 (No such file or directory)` after native linking

If the executable exists but `sbt .../run` reports it cannot be found, the ELF
interpreter baked into the binary may not exist on your distribution. This can
happen on Exherbo when a Homebrew/LLVM toolchain chooses
`/usr/x86_64-unknown-linux-gnu/...`, while the host uses
`/usr/x86_64-pc-linux-gnu/...`.

Check:

```bash
readelf -l examples/camera-2d/target/scala-3.7.3/rayscal-camera-2d | grep interpreter
readelf -l /bin/sh | grep interpreter
```

`build.sbt` detects the interpreter from `/bin/sh` and passes it to the linker
with `-Wl,--dynamic-linker=...`. If you changed toolchains, relink:

```bash
sbt camera2d/nativeLink camera2d/run
```

### Missing X11, OpenGL, or audio symbols

Install raylib's native dependencies for your distribution. On Ubuntu/Debian:

```bash
sudo apt install libasound2-dev libx11-dev libxrandr-dev libxi-dev libgl1-mesa-dev libglu1-mesa-dev libxcursor-dev libxinerama-dev
```

If you built raylib statically, you may need to add extra libraries to
`linkingOptions`. Dynamic linking is simpler for this starter project.

### Render textures crash immediately

Render textures use a C shim instead of a direct by-value `LoadRenderTexture`
extern. If you see a crash around framebuffer creation, clean and relink so the
shim and native compile options are refreshed:

```bash
sbt clean
sbt renderTexture/run
```

## Roadmap

Short-term:

- Expand raylib core coverage carefully
- Add small examples for each newly supported area
- Validate Linux install instructions across common distributions
- Keep Ubuntu CI green for core compilation and native example linking
- Keep resource-owning APIs paired with `with...` helpers where practical

Planned binding areas:

- raylib core: windows, drawing lifecycle, timing, files, logging
- rlgl: immediate-mode drawing, render state, buffers, textures, shaders,
  framebuffers, compute helpers
- raymath: vectors, matrices, quaternions, transforms
- textures: image loading, texture upload, drawing textures, render textures,
  image manipulation
- audio: device lifecycle, sounds, music streams
- 3D camera: camera types, perspective setup, 3D drawing lifecycle
- input: keyboard, mouse, gamepad
- shapes: rectangles, lines, circles, polygons, collision helpers

Later when i feeel like doing it (which i will):

- macOS support notes
- Windows support notes
- More complete type coverage
- Optional generated raw bindings after the hand-written API settles
- A friendlier Scala wrapper layer over the raw externs

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

## License

MIT. See [LICENSE](LICENSE).
