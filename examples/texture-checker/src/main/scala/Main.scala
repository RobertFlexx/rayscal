import rayscal.*

@main def textureChecker(): Unit =
  Window.withWindow(800, 450, "rayscal - texture checker"):
    Window.setTargetFps(60)

    Images.withChecked(256, 256, 32, 32, Colors.SKYBLUE, Colors.RAYWHITE): image =>
      Textures.withTextureFromImage(image): texture =>
        Textures.setFilter(texture, TextureFilters.Point)

        while !Window.shouldClose do
          val rotation = (Time.elapsed * 35.0).toFloat

          Drawing.frame:
            val source = Rect(0.0f, 0.0f, Textures.width(texture).toFloat, Textures.height(texture).toFloat)
            val dest = Rect(400.0f, 230.0f, 256.0f, 256.0f)
            val origin = Vector.vector2(128.0f, 128.0f)

            Drawing.clear(Colors.DARKGRAY)
            Drawing.text("Generated Image -> Texture2D", 24, 24, 28, Colors.RAYWHITE)
            Drawing.text(s"${Textures.width(texture)}x${Textures.height(texture)}, id=${Textures.id(texture)}", 24, 58, 18, Colors.LIGHTGRAY)
            Textures.drawPro(texture, source, dest, origin, rotation, Colors.WHITE)
