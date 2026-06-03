import rayscal.*

@main def bouncingBall(): Unit =
  Window.withWindow(800, 450, "rayscal - bouncing ball"):
    Window.setTargetFps(60)

    var x = 400.0f
    var y = 225.0f
    var dx = 260.0f
    var dy = 190.0f
    val radius = 24.0f

    while !Window.shouldClose do
      val dt = Time.frameTime

      x += dx * dt
      y += dy * dt

      if x - radius < 0 || x + radius > Window.screenWidth then
        dx = -dx
        x = math.max(radius, math.min(Window.screenWidth - radius, x)).toFloat

      if y - radius < 0 || y + radius > Window.screenHeight then
        dy = -dy
        y = math.max(radius, math.min(Window.screenHeight - radius, y)).toFloat

      Drawing.frame:
        Drawing.clear(Colors.RAYWHITE)
        Drawing.circle(x.toInt, y.toInt, radius, Colors.BLUE)
        Drawing.text("rayscal bouncing ball", 24, 24, 24, Colors.BLACK)
