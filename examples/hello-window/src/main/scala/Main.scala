import rayscal.*

@main def helloWindow(): Unit =
  Window.withWindow(800, 450, "rayscal - hello window"):
    Window.setTargetFps(60)

    while !Window.shouldClose do
      Drawing.frame:
        val message = "Hello from rayscal!"
        val fontSize = 28
        val x = (Window.screenWidth - Drawing.measureText(message, fontSize)) / 2
        val y = (Window.screenHeight - fontSize) / 2

        Drawing.clear(Colors.RAYWHITE)
        Drawing.text(message, x, y, fontSize, Colors.DARKGRAY)
        Drawing.text("A minimal, scoped raylib window", 24, Window.screenHeight - 44, 18, Colors.GRAY)
