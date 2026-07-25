import rayscal.*

@main def rlglTriangle(): Unit =
  Window.withWindow(800, 450, "rayscal - rlgl triangle"):
    Window.setTargetFps(60)
    var rotation = 0.0f

    while !Window.shouldClose do
      rotation = (rotation + Time.frameTime * 45.0f) % 360.0f

      Drawing.frame:
        Drawing.clear(Colors.RAYWHITE)

        // Immediate-mode rlgl draw with a scoped matrix transform.
        // Vertices are expressed in local space; translate/rotate place the triangle.
        Rlgl.pushMatrix:
          Rlgl.translate(Window.screenWidth / 2.0f, Window.screenHeight / 2.0f + 20.0f, 0.0f)
          Rlgl.rotate(rotation, 0.0f, 0.0f, 1.0f)
          Rlgl.begin(Rlgl.DrawMode.Triangles):
            Rlgl.color4ub(230, 41, 55, 255)
            Rlgl.vertex2f(0.0f, -120.0f)
            Rlgl.color4ub(0, 228, 48, 255)
            Rlgl.vertex2f(-110.0f, 90.0f)
            Rlgl.color4ub(0, 121, 241, 255)
            Rlgl.vertex2f(110.0f, 90.0f)

        // Flush rlgl batch before higher-level Drawing calls that also use the batch.
        Rlgl.drawRenderBatchActive()

        Drawing.text("rlgl immediate mode", 24, 24, 26, Colors.BLACK)
        Drawing.text("Scoped matrix transform + per-vertex color interpolation", 24, 58, 18, Colors.DARKGRAY)
        Drawing.text(s"rlgl backend version: ${Rlgl.version}", 24, Window.screenHeight - 36, 16, Colors.GRAY)
