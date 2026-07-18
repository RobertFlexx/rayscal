import rayscal.*

@main def renderTexture(): Unit =
  Window.withWindow(900, 520, "rayscal - render texture"):
    Window.setTargetFps(60)

    val targetWidth = 320
    val targetHeight = 240
    RenderTargets.withRenderTexture(targetWidth, targetHeight): target =>
      require(RenderTargets.isValid(target), "Could not create render texture")
      val texture = RenderTargets.texture(target)

      while !Window.shouldClose do
        val markerX = 34.0f + ((math.sin(Time.elapsed * 2.0) + 1.0) * 110.0).toFloat

        Drawing.textureMode(target):
          Drawing.clear(Colors.SKYBLUE)
          Shapes.rectangle(20, 20, 280, 200, Colors.WHITE)
          Shapes.rectangleLines(20, 20, 280, 200, Colors.DARKBLUE)
          Shapes.circle(Vector.vector2(markerX, 62.0f), 18.0f, Colors.ORANGE)
          Drawing.text("TOP", 34, 36, 22, Colors.MAROON)
          Drawing.text("rendered offscreen", 66, 108, 22, Colors.BLACK)
          Shapes.triangle(Vector.vector2(160.0f, 188.0f), Vector.vector2(132.0f, 218.0f), Vector.vector2(188.0f, 218.0f), Colors.GREEN)

        Drawing.frame:
          val source = Rect(0.0f, 0.0f, Textures.width(texture).toFloat, -Textures.height(texture).toFloat)
          val destination = Rect(290.0f, 135.0f, 320.0f, 240.0f)
          val origin = Vector.vector2(0.0f, 0.0f)

          Drawing.clear(Colors.RAYWHITE)
          Drawing.text("RenderTexture2D + borrowed TextureView", 24, 24, 28, Colors.BLACK)
          Drawing.text("Negative source height corrects render-target orientation", 24, 60, 18, Colors.DARKGRAY)
          Textures.drawPro(texture, source, destination, origin, 0.0f, Colors.WHITE)
          Shapes.rectangleLines(290, 135, 320, 240, Colors.DARKGRAY)
          Drawing.text("The attachment is owned by the render target", 276, 400, 18, Colors.DARKBLUE)
