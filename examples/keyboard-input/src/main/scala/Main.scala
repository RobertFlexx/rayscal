import rayscal.*

@main def keyboardInput(): Unit =
  Window.withWindow(800, 450, "rayscal - keyboard input"):
    Window.setTargetFps(60)

    var x = 400.0f
    var y = 225.0f
    val radius = 24.0f
    val speed = 260.0f

    while !Window.shouldClose do
      val dt = Time.frameTime

      if Keyboard.isDown(Keys.Right) then x += speed * dt
      if Keyboard.isDown(Keys.Left) then x -= speed * dt
      if Keyboard.isDown(Keys.Down) then y += speed * dt
      if Keyboard.isDown(Keys.Up) then y -= speed * dt

      x = math.max(radius, math.min(Window.screenWidth - radius, x)).toFloat
      y = math.max(radius, math.min(Window.screenHeight - radius, y)).toFloat

      Drawing.frame:
        Drawing.clear(Colors.RAYWHITE)
        Drawing.circle(x.toInt, y.toInt, radius, Colors.GREEN)
        Drawing.text("Move the circle with arrow keys", 24, 24, 24, Colors.BLACK)
