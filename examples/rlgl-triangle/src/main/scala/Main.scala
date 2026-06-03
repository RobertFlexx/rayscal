import rayscal.*

@main def rlglTriangle(): Unit =
  Window.withWindow(800, 450, "rayscal - rlgl triangle"):
    Window.setTargetFps(60)

    while !Window.shouldClose do
      Drawing.frame:
        Drawing.clear(Colors.RAYWHITE)
        Drawing.text("rlgl immediate-mode triangle", 24, 24, 24, Colors.BLACK)

        Rlgl.begin(Rlgl.DrawMode.Triangles):
          Rlgl.color4ub(230, 41, 55, 255)
          Rlgl.vertex2f(400.0f, 130.0f)

          Rlgl.color4ub(0, 228, 48, 255)
          Rlgl.vertex2f(280.0f, 330.0f)

          Rlgl.color4ub(0, 121, 241, 255)
          Rlgl.vertex2f(520.0f, 330.0f)
