import rayscal.*

@main def camera2D(): Unit =
  Window.withWindow(900, 520, "rayscal - camera 2D"):
    Window.setTargetFps(60)

    var targetX = 0.0f
    var targetY = 0.0f
    var zoom = 1.0f

    while !Window.shouldClose do
      val speed = 280.0f * Time.frameTime / zoom
      if Keyboard.isDown(Keys.Right) || Keyboard.isDown(Keys.D) then targetX += speed
      if Keyboard.isDown(Keys.Left) || Keyboard.isDown(Keys.A) then targetX -= speed
      if Keyboard.isDown(Keys.Down) || Keyboard.isDown(Keys.S) then targetY += speed
      if Keyboard.isDown(Keys.Up) || Keyboard.isDown(Keys.W) then targetY -= speed
      zoom = math.max(0.25f, math.min(3.0f, zoom + Mouse.wheel * 0.1f)).toFloat

      Drawing.frame:
        val camera = Cameras.camera2D(
          offset = Vector.vector2(Window.screenWidth / 2.0f, Window.screenHeight / 2.0f),
          target = Vector.vector2(targetX, targetY),
          rotation = 0.0f,
          zoom = zoom
        )
        val worldMouse = ScreenSpace.screenToWorld2D(Mouse.position, camera)

        Drawing.clear(Colors.RAYWHITE)
        Drawing.mode2D(camera):
          for x <- -8 to 8 do
            Shapes.line(x * 100, -800, x * 100, 800, Colors.LIGHTGRAY)
          for y <- -6 to 6 do
            Shapes.line(-900, y * 100, 900, y * 100, Colors.LIGHTGRAY)

          Shapes.line(-900, 0, 900, 0, Colors.RED)
          Shapes.line(0, -800, 0, 800, Colors.BLUE)

          Shapes.rectangle(-120, -90, 240, 180, Colors.SKYBLUE)
          Shapes.rectangleLines(-120, -90, 240, 180, Colors.DARKBLUE)
          Shapes.circle(0, 0, 18.0f, Colors.RED)
          Shapes.circle(worldMouse, 10.0f, Colors.BLACK)
          val mouseX = worldMouse._1.toInt
          val mouseY = worldMouse._2.toInt
          Shapes.line(mouseX - 18, mouseY, mouseX + 18, mouseY, Colors.BLACK)
          Shapes.line(mouseX, mouseY - 18, mouseX, mouseY + 18, Colors.BLACK)

        Shapes.rectangle(16, 16, 600, 76, Colors.WHITE)
        Shapes.rectangleLines(16, 16, 600, 76, Colors.DARKGRAY)
        Drawing.text("Camera2D demo", 32, 28, 24, Colors.BLACK)
        Drawing.text("WASD/arrows pan | mouse wheel zoom | black crosshair is world-space mouse", 32, 58, 16, Colors.DARKGRAY)
        Drawing.text(f"target=($targetX%.1f, $targetY%.1f), zoom=$zoom%.2f", 650, 28, 18, Colors.DARKBLUE)
