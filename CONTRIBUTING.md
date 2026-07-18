# Contributing to rayscal

Thanks for helping build rayscal.

rayscal is under active development and intentionally thin. The first goal is a correct,
predictable Scala Native surface over raylib's C API. Friendlier Scala wrappers
can grow on top once the raw layer is trustworthy.

## Ground rules

- Keep raw bindings close to raylib names, signatures, and behavior.
- Add small helpers only when they remove repetitive Scala Native interop noise.
- Do not claim support for raylib areas that are not bound and tested.
- Prefer tiny, readable examples over large demos.
- Keep Linux working first. Add macOS and Windows notes as they are validated.
- Include comments for native interop details that are easy to misuse.
- Do not pass resource-bearing or nested structs by value across the friendly
  API boundary. Use pointer-based C shims.
- Keep shims in `modules/core/src/main/resources/scala-native` small and
  mechanical.

## Adding bindings

1. Add the extern declaration in `modules/core/src/main/scala/rayscal/raw/Raylib.scala`.
2. Add any required C struct aliases in `modules/core/src/main/scala/rayscal/raw/package.scala`.
3. Add native shims in `modules/core/src/main/resources/scala-native` only for
   ABI-sensitive cases that should not cross Scala Native as direct returns.
4. Add color/vector/string helpers only if the raw API becomes noisy in examples.
5. Add a small example or test program when practical.
6. Update the README support matrix and troubleshooting notes.

## Style

- Scala 3 syntax is preferred.
- Keep public names boring and searchable.
- Avoid broad abstractions until several examples prove they are useful.
- Keep generated bindings out of the repository for now.

## Local checks

```bash
sbt check
```

This compiles the core, runs the ABI size/field-layout check, and links every
example.

Run examples manually because they open native windows:

```bash
sbt helloWindow/run
sbt bouncingBall/run
```
