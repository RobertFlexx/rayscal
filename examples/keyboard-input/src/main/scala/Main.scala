import rayscal.*

@main def keyboardInput(): Unit =
  Window.withWindow(800, 450, "rayscal - keyboard input"):
    Window.setTargetFps(60)

    var x = 400.0f
    var y = 225.0f
    val radius = 24.0f
    val speed = 260.0f

    while !Window.shouldClose do
      val horizontal =
        (if Keyboard.isDown(Keys.Right) || Keyboard.isDown(Keys.D) then 1.0f else 0.0f) -
          (if Keyboard.isDown(Keys.Left) || Keyboard.isDown(Keys.A) then 1.0f else 0.0f)
      val vertical =
        (if Keyboard.isDown(Keys.Down) || Keyboard.isDown(Keys.S) then 1.0f else 0.0f) -
          (if Keyboard.isDown(Keys.Up) || Keyboard.isDown(Keys.W) then 1.0f else 0.0f)
      val length = math.sqrt(horizontal * horizontal + vertical * vertical).toFloat
      val scale = if length > 0.0f then 1.0f / length else 0.0f
      val dt = math.min(Time.frameTime, 0.05f).toFloat

      x = math.max(radius, math.min(Window.screenWidth - radius, x + horizontal * scale * speed * dt)).toFloat
      y = math.max(radius, math.min(Window.screenHeight - radius, y + vertical * scale * speed * dt)).toFloat

      Drawing.frame:
        Drawing.clear(Colors.RAYWHITE)
        Shapes.circle(Vector.vector2(x, y), radius, Colors.GREEN)
        Shapes.circleLines(x.toInt, y.toInt, radius, Colors.LIME)
        Drawing.text("Move with WASD or arrow keys", 24, 24, 24, Colors.DARKGRAY)
        Drawing.text("Diagonal input is normalized", 24, 56, 18, Colors.GRAY)
