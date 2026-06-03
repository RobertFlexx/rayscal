import rayscal.*

@main def shapesGallery(): Unit =
  Window.withWindow(900, 520, "rayscal - shapes gallery"):
    Window.setTargetFps(60)

    while !Window.shouldClose do
      Drawing.frame:
        Drawing.clear(Colors.RAYWHITE)
        Drawing.text("Shapes + mouse input", 24, 24, 28, Colors.BLACK)
        Drawing.text("Move the mouse. Hold left button to thicken the line.", 24, 58, 18, Colors.DARKGRAY)

        val mouse = Mouse.position
        val lineThick = if Mouse.isDown(MouseButtons.Left) then 8.0f else 3.0f

        Shapes.rectangle(40, 110, 180, 120, Colors.SKYBLUE)
        Shapes.rectangleLines(40, 110, 180, 120, Colors.DARKBLUE)
        Shapes.circle(330, 170, 62.0f, Colors.ORANGE)
        Shapes.circleLines(330, 170, 62.0f, Colors.MAROON)
        Shapes.ellipse(520, 170, 86.0f, 48.0f, Colors.PURPLE)
        Shapes.ellipseLines(520, 170, 86.0f, 48.0f, Colors.VIOLET)

        Shapes.triangle(
          Vector.vector2(690.0f, 245.0f),
          Vector.vector2(780.0f, 110.0f),
          Vector.vector2(850.0f, 245.0f),
          Colors.GREEN
        )

        Shapes.polygon(Vector.vector2(150.0f, 380.0f), 6, 62.0f, Time.elapsed.toFloat * 30.0f, Colors.GOLD)
        Shapes.ring(Vector.vector2(360.0f, 380.0f), 38.0f, 68.0f, 0.0f, 300.0f, 48, Colors.RED)
        Shapes.line(Vector.vector2(480.0f, 380.0f), mouse, lineThick, Colors.BLUE)
        Shapes.circle(mouse, 10.0f, Colors.BLACK)
        Drawing.fps(820, 20)
