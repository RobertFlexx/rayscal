import rayscal.*

private def reflect(position: Float, velocity: Float, minimum: Float, maximum: Float): (Float, Float) =
  var nextPosition = position
  var nextVelocity = velocity
  while nextPosition < minimum || nextPosition > maximum do
    if nextPosition < minimum then
      nextPosition = minimum + (minimum - nextPosition)
      nextVelocity = math.abs(nextVelocity).toFloat
    else
      nextPosition = maximum - (nextPosition - maximum)
      nextVelocity = -math.abs(nextVelocity).toFloat
  (nextPosition, nextVelocity)

@main def bouncingBall(): Unit =
  Window.withWindow(800, 450, "rayscal - bouncing ball"):
    Window.setTargetFps(60)

    var x = 400.0f
    var y = 225.0f
    var velocityX = 260.0f
    var velocityY = 190.0f
    val radius = 24.0f

    while !Window.shouldClose do
      val dt = math.min(Time.frameTime, 0.05f).toFloat
      val (nextX, nextVelocityX) = reflect(x + velocityX * dt, velocityX, radius, Window.screenWidth - radius)
      val (nextY, nextVelocityY) = reflect(y + velocityY * dt, velocityY, radius, Window.screenHeight - radius)
      x = nextX
      y = nextY
      velocityX = nextVelocityX
      velocityY = nextVelocityY

      Drawing.frame:
        Drawing.clear(Colors.RAYWHITE)
        Shapes.circle(Vector.vector2(x, y), radius, Colors.BLUE)
        Drawing.text("Frame-rate independent movement with reflected overshoot", 24, 24, 20, Colors.DARKGRAY)
        Drawing.fps(Window.screenWidth - 90, 20)
