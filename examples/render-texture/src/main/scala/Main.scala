import rayscal.*

@main def renderTexture(): Unit =
  Window.withWindow(900, 520, "rayscal - render texture"):
    Window.setTargetFps(60)

    RenderTargets.withRenderTexture(320, 240): target =>
      while !Window.shouldClose do
        Drawing.textureMode(target):
          Drawing.clear(Colors.RAYWHITE)
          Shapes.rectangle(0, 0, 320, 240, Colors.SKYBLUE)
          Shapes.rectangle(24, 24, 272, 192, Colors.WHITE)
          Shapes.circle(160, 120, 64.0f, Colors.ORANGE)
          Shapes.rectangleLines(24, 24, 272, 192, Colors.DARKBLUE)
          Drawing.text("offscreen target", 64, 106, 24, Colors.BLACK)

        Drawing.frame:
          val texture = RenderTargets.texture(target)
          val source = Rect(0.0f, 0.0f, Textures.width(texture).toFloat, -Textures.height(texture).toFloat)
          val dest = Rect(530.0f, 160.0f, 320.0f, 240.0f)
          val origin = Vector.vector2(0.0f, 0.0f)

          Drawing.clear(Colors.RAYWHITE)
          Drawing.text("RenderTexture2D demo", 24, 24, 28, Colors.BLACK)
          Drawing.text("Left: ordinary screen drawing | Right: texture produced by BeginTextureMode", 24, 58, 18, Colors.DARKGRAY)

          Shapes.rectangle(60, 160, 320, 240, Colors.SKYBLUE)
          Shapes.rectangle(84, 184, 272, 192, Colors.WHITE)
          Shapes.circle(220, 280, 64.0f, Colors.ORANGE)
          Shapes.rectangleLines(84, 184, 272, 192, Colors.DARKBLUE)
          Drawing.text("screen drawing", 124, 266, 24, Colors.BLACK)
          Shapes.rectangleLines(60, 160, 320, 240, Colors.DARKGRAY)
          Drawing.text("screen", 60, 420, 20, Colors.DARKGRAY)

          Textures.drawPro(texture, source, dest, origin, 0.0f, Colors.WHITE)
          Shapes.rectangleLines(530, 160, 320, 240, Colors.DARKGRAY)
          Drawing.text("render texture", 530, 420, 20, Colors.DARKGRAY)
