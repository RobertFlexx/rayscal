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
- Scala Native C shims are required for every C function that accepts or returns
  a struct by value (parameters and return values). Put shims in
  `modules/core/src/main/resources/scala-native` and declare them from
  `rayscal.raw.RayscalNative`.
- Do not declare struct-by-value entry points on `raw.Raylib` `@extern` objects.
- Raw C structs must not escape temporary `stackalloc` or `Zone` lifetimes.
  Copy results into Scala-owned values before leaving the allocation scope.
- Friendly public values (`Color`, `Vector2`, `Rectangle`, cameras, rays, …)
  must remain Scala-owned immutable data — not aliases or views into native
  memory.
- Keep shims in `modules/core/src/main/resources/scala-native` small and
  mechanical.

## Adding bindings

1. For FFI-safe scalar / pointer / `CString` APIs, add the extern declaration in
   `modules/core/src/main/scala/rayscal/raw/Raylib.scala` (or `Rlgl.scala`).
2. For any API that takes or returns a struct by value, add a pointer-based C
   shim in `modules/core/src/main/resources/scala-native` and an `@extern`
   declaration in `RayscalNative.scala`.
3. Keep raw `CStruct` layouts in `modules/core/src/main/scala/rayscal/raw`
   for marshaling only; do not expose them as the public value API.
4. Marshal through `NativeMarshal` (or equivalent Zone-scoped helpers) and return
   Scala-owned values from friendly wrappers.
5. Add a small example or extend `ffiSafety` / `abiCheck` when practical.
6. Update the README support notes if the tested surface changes.

## Style

- Scala 3 syntax is preferred.
- Keep public names boring and searchable.
- Avoid broad abstractions until several examples prove they are useful.
- Keep generated bindings out of the repository for now.

## Local checks

```bash
sbt check
```

This compiles the core, runs headless FFI safety checks and the graphical ABI
smoke test, and links every example. On a machine without a display, run
`xvfb-run -a sbt check` (as CI does).

Run examples manually because they open native windows:

```bash
sbt helloWindow/run
sbt bouncingBall/run
```
