import rayscal.*

@main def rlglTriangle(): Unit =
  Window.withWindow(800, 450, "rayscal - rlgl triangle"):
    Window.setTargetFps(60)

    while !Window.shouldClose do
      val rotation = ((Time.elapsed * 45.0) % 360.0).toFloat

      Drawing.frame:
        Drawing.clear(Colors.RAYWHITE)

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

        Drawing.text("rlgl immediate mode", 24, 24, 26, Colors.BLACK)
        Drawing.text("Scoped matrix transform + per-vertex color interpolation", 24, 58, 18, Colors.DARKGRAY)
        Drawing.text(s"rlgl backend version: ${Rlgl.version}", 24, Window.screenHeight - 36, 16, Colors.GRAY)
