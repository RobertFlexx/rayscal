import rayscal.*

@main def helloWindow(): Unit =
  Window.withWindow(800, 450, "rayscal - hello window"):
    Window.setTargetFps(60)

    while !Window.shouldClose do
      Drawing.frame:
        Drawing.clear(Colors.RAYWHITE)
        Drawing.text("Hello from rayscal!", 250, 200, 24, Colors.BLACK)
