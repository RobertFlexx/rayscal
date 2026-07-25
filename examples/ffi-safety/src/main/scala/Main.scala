package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.*

@main def ffiSafety(): Unit =
  var failures = 0

  def assertTrue(condition: Boolean, message: String): Unit =
    if !condition then
      failures += 1
      System.err.println(s"FAIL: $message")

  def assertEq[A](actual: A, expected: A, message: String): Unit =
    assertTrue(actual == expected, s"$message (expected=$expected, actual=$actual)")

  RaylibAbi.validate()
  assertTrue(true, "RaylibAbi.validate completed")

  // 1. Vector2 output with exact expected values
  val vector = NativeMarshal.readVector2(RayscalNative.TestReturnVector2(_))
  assertEq(vector.x, 1.5f, "TestReturnVector2.x")
  assertEq(vector.y, -2.25f, "TestReturnVector2.y")

  // 2. Color output with exact expected rgba
  val color = NativeMarshal.readColor(RayscalNative.TestReturnColor(_))
  assertEq(color.r, 10, "TestReturnColor.r")
  assertEq(color.g, 20, "TestReturnColor.g")
  assertEq(color.b, 30, "TestReturnColor.b")
  assertEq(color.a, 40, "TestReturnColor.a")

  // 3. Rectangle parameter
  assertTrue(
    Zone(RayscalNative.TestAcceptRectangle(NativeMarshal.rectangle(Rectangle(1.0f, 2.0f, 3.0f, 4.0f)))),
    "TestAcceptRectangle"
  )

  // 4. Multiple struct parameters (Vector2, Vector2, Color)
  assertTrue(
    Zone:
      RayscalNative.TestAcceptLineColor(
        NativeMarshal.vector2(Vector2(0.0f, 0.0f)),
        NativeMarshal.vector2(Vector2(100.0f, 50.0f)),
        NativeMarshal.color(Colors.RED)
      ),
    "TestAcceptLineColor"
  )

  // 5. Camera2D and Camera3D marshaling
  val cam2d = Camera2D(Vector2(400.0f, 300.0f), Vector2(10.0f, 20.0f), 0.0f, 2.0f)
  assertTrue(Zone(RayscalNative.TestAcceptCamera2D(NativeMarshal.camera2D(cam2d))), "TestAcceptCamera2D")

  val cam3d = Camera3D(Vector3(1.0f, 2.0f, 3.0f), Vector3(0.0f, 0.0f, 0.0f), Vector3(0.0f, 1.0f, 0.0f), 45.0f, CameraProjection.Perspective)
  assertTrue(Zone(RayscalNative.TestAcceptCamera3D(NativeMarshal.camera3D(cam3d))), "TestAcceptCamera3D")

  // 6. Larger resource-metadata struct (Texture2D fields)
  Zone:
    val texture = alloc[raw.Texture2D]()
    RayscalNative.TestReturnTextureMeta(texture)
    assertEq((!texture)._1.toInt, 42, "TextureMeta.id")
    assertEq((!texture)._2, 64, "TextureMeta.width")
    assertEq((!texture)._3, 32, "TextureMeta.height")
    assertEq((!texture)._4, 1, "TextureMeta.mipmaps")
    assertEq((!texture)._5, 7, "TextureMeta.format")

  // 7. Repeated calls to expose memory corruption
  var i = 0
  while i < 10_000 do
    val v = NativeMarshal.readVector2(RayscalNative.TestReturnVector2(_))
    val c = NativeMarshal.readColor(RayscalNative.TestReturnColor(_))
    if v.x != 1.5f || v.y != -2.25f || c.r != 10 || c.g != 20 || c.b != 30 || c.a != 40 then
      failures += 1
      System.err.println(s"FAIL: loop corruption at iteration $i")
      i = 10_000
    else
      i += 1

  // 8. Stack churn after receiving a returned value
  val afterChurn = NativeMarshal.readVector2(RayscalNative.TestReturnVector2(_))
  RayscalNative.TestChurnStack()
  assertEq(afterChurn.x, 1.5f, "stack-churn Vector2.x")
  assertEq(afterChurn.y, -2.25f, "stack-churn Vector2.y")

  val faded = Colors.fade(Colors.RED, 0.5f)
  RayscalNative.TestChurnStack()
  assertEq(faded.r, Colors.RED.r, "stack-churn fade.r")
  assertEq(faded.a, 127, "stack-churn fade.a") // raylib Fade uses alpha*255 rounding

  // 9. Values stored in collections prove Scala ownership
  val collected =
    (0 until 64).map: n =>
      val v = NativeMarshal.readVector2(RayscalNative.TestReturnVector2(_))
      Vector2(v.x + n, v.y - n)
  RayscalNative.TestChurnStack()
  assertEq(collected(0), Vector2(1.5f, -2.25f), "collection[0]")
  assertEq(collected(10), Vector2(11.5f, -12.25f), "collection[10]")
  assertEq(collected(63), Vector2(64.5f, -65.25f), "collection[63]")

  // Friendly Color/Vector factories do not require Zone
  val noZoneColor = Colors.rgba(1, 2, 3, 4)
  val noZoneVec = Vector.vector2(9.0f, 8.0f)
  assertEq(noZoneColor, Color(1, 2, 3, 4), "Colors.rgba without Zone")
  assertEq(noZoneVec, Vector2(9.0f, 8.0f), "Vector.vector2 without Zone")

  // 10. Ownership: unload once, reject use-after-unload
  val image = Images.solid(8, 8, Colors.BLUE)
  assertTrue(Images.isValid(image), "image valid after create")
  Images.unload(image)
  var useAfterUnloadBlocked = false
  try
    Images.isValid(image)
  catch
    case _: IllegalStateException => useAfterUnloadBlocked = true
  assertTrue(useAfterUnloadBlocked, "use-after-unload blocked")

  // Double unload is a no-op (must not crash / double-free)
  Images.unload(image)

  if failures == 0 then
    println("ffi-safety: all assertions passed")
  else
    System.err.println(s"ffi-safety: $failures assertion(s) failed")
    sys.exit(1)
