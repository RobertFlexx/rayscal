import rayscal.*
import scala.scalanative.unsafe.*

@main def abiCheck(): Unit =
  var failures = 0

  def assertTrue(condition: Boolean, message: String): Unit =
    if !condition then
      failures += 1
      System.err.println(s"FAIL: $message")

  RaylibAbi.validate()

  // Graphical smoke tests for struct shims that require a window context.
  // Deterministic headless assertions live in ffiSafety.
  Window.withWindow(100, 100, "abi-check"):
    Zone:
      val delta = Mouse.delta
      assertTrue(delta.x.isFinite && delta.y.isFinite, "Mouse.delta returns finite Vector2")

      val faded = Colors.fade(Colors.RED, 0.5f)
      assertTrue(faded.r == Colors.RED.r && faded.g == Colors.RED.g && faded.b == Colors.RED.b, "Colors.fade preserves rgb")
      assertTrue(faded.a == 127, "Colors.fade alpha")

      Drawing.clear(Colors.RAYWHITE)
      Shapes.rectangle(Rect(10.0f, 10.0f, 100.0f, 100.0f), Colors.RED)
      Shapes.line(Vector.vector2(0.0f, 0.0f), Vector.vector2(100.0f, 100.0f), Colors.BLUE)

      val cam2d = Cameras.camera2D(Vector.vector2(400.0f, 300.0f), Vector.vector2(0.0f, 0.0f), 0.0f, 1.0f)
      Drawing.mode2D(cam2d):
        ()

      val cam3d = Cameras.camera3D(
        Vector.vector3(0.0f, 10.0f, 10.0f),
        Vector.vector3(0.0f, 0.0f, 0.0f),
        Vector.vector3(0.0f, 1.0f, 0.0f),
        45.0f,
        CameraProjection.Perspective
      )
      Drawing.mode3D(cam3d):
        ()

      Drawing.text("Test", 10, 10, 20, Colors.DARKGRAY)
      Shapes.circle(Vector.vector2(50.0f, 50.0f), 25.0f, Colors.GREEN)
      Shapes.pixel(Vector.vector2(1.0f, 1.0f), Colors.BLACK)

  if failures == 0 then
    println(s"raylib ${RaylibAbi.raylibVersion} ABI size/layout checks and graphical smoke tests passed")
  else
    System.err.println(s"abi-check: $failures assertion(s) failed")
    sys.exit(1)
