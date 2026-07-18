import rayscal.*
import scala.scalanative.unsafe.Zone

@main def camera2D(): Unit =
  Window.withWindow(900, 520, "rayscal - camera 2D"):
    Window.setTargetFps(60)

    var targetX = 0.0f
    var targetY = 0.0f
    var zoom = 1.0f

    while !Window.shouldClose do
      Zone:
        val horizontal =
          (if Keyboard.isDown(Keys.Right) || Keyboard.isDown(Keys.D) then 1.0f else 0.0f) -
            (if Keyboard.isDown(Keys.Left) || Keyboard.isDown(Keys.A) then 1.0f else 0.0f)
        val vertical =
          (if Keyboard.isDown(Keys.Down) || Keyboard.isDown(Keys.S) then 1.0f else 0.0f) -
            (if Keyboard.isDown(Keys.Up) || Keyboard.isDown(Keys.W) then 1.0f else 0.0f)
        val directionLength = math.sqrt(horizontal * horizontal + vertical * vertical).toFloat
        val directionScale = if directionLength > 0.0f then 1.0f / directionLength else 0.0f
        val dt = math.min(Time.frameTime, 0.05f).toFloat
        val panDistance = 280.0f * dt / zoom
        targetX += horizontal * directionScale * panDistance
        targetY += vertical * directionScale * panDistance

        val offset = Vector.vector2(Window.screenWidth / 2.0f, Window.screenHeight / 2.0f)
        val mouse = Mouse.position
        val cameraBeforeZoom = Cameras.camera2D(offset, Vector.vector2(targetX, targetY), 0.0f, zoom)
        val worldBeforeZoom = ScreenSpace.screenToWorld2D(mouse, cameraBeforeZoom)
        val zoomFactor = math.pow(1.12, Mouse.wheel.toDouble).toFloat
        zoom = math.max(0.25f, math.min(4.0f, zoom * zoomFactor)).toFloat
        val cameraAfterZoom = Cameras.camera2D(offset, Vector.vector2(targetX, targetY), 0.0f, zoom)
        val worldAfterZoom = ScreenSpace.screenToWorld2D(mouse, cameraAfterZoom)
        targetX += worldBeforeZoom._1 - worldAfterZoom._1
        targetY += worldBeforeZoom._2 - worldAfterZoom._2

        val camera = Cameras.camera2D(offset, Vector.vector2(targetX, targetY), 0.0f, zoom)
        val worldMouse = ScreenSpace.screenToWorld2D(mouse, camera)
        val topLeft = ScreenSpace.screenToWorld2D(Vector.vector2(0.0f, 0.0f), camera)
        val bottomRight = ScreenSpace.screenToWorld2D(Vector.vector2(Window.screenWidth.toFloat, Window.screenHeight.toFloat), camera)
        val firstGridX = math.floor(math.min(topLeft._1, bottomRight._1) / 100.0f).toInt
        val lastGridX = math.ceil(math.max(topLeft._1, bottomRight._1) / 100.0f).toInt
        val firstGridY = math.floor(math.min(topLeft._2, bottomRight._2) / 100.0f).toInt
        val lastGridY = math.ceil(math.max(topLeft._2, bottomRight._2) / 100.0f).toInt

        Drawing.frame:
          Drawing.clear(Colors.RAYWHITE)
          Drawing.mode2D(camera):
            for x <- firstGridX to lastGridX do
              Shapes.line(x * 100, firstGridY * 100, x * 100, lastGridY * 100, Colors.LIGHTGRAY)
            for y <- firstGridY to lastGridY do
              Shapes.line(firstGridX * 100, y * 100, lastGridX * 100, y * 100, Colors.LIGHTGRAY)

            Shapes.line(firstGridX * 100, 0, lastGridX * 100, 0, Colors.RED)
            Shapes.line(0, firstGridY * 100, 0, lastGridY * 100, Colors.BLUE)
            Shapes.rectangle(-120, -90, 240, 180, Colors.SKYBLUE)
            Shapes.rectangleLines(-120, -90, 240, 180, Colors.DARKBLUE)
            Shapes.circle(worldMouse, 10.0f, Colors.BLACK)
            Shapes.line(Vector.vector2(worldMouse._1 - 18.0f, worldMouse._2), Vector.vector2(worldMouse._1 + 18.0f, worldMouse._2), Colors.BLACK)
            Shapes.line(Vector.vector2(worldMouse._1, worldMouse._2 - 18.0f), Vector.vector2(worldMouse._1, worldMouse._2 + 18.0f), Colors.BLACK)

          Shapes.rectangle(16, 16, 620, 76, Colors.WHITE)
          Shapes.rectangleLines(16, 16, 620, 76, Colors.DARKGRAY)
          Drawing.text("Camera2D: cursor-anchored zoom", 32, 28, 24, Colors.BLACK)
          Drawing.text("WASD/arrows pan | wheel zooms without moving the world under the cursor", 32, 58, 16, Colors.DARKGRAY)
          Drawing.text(f"target=($targetX%.1f, $targetY%.1f)  zoom=$zoom%.2f", 650, 28, 17, Colors.DARKBLUE)
