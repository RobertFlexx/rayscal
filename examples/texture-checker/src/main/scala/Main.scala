import rayscal.*

@main def textureChecker(): Unit =
  Window.withWindow(800, 450, "rayscal - texture checker"):
    Window.setTargetFps(60)

    Images.withChecked(256, 256, 32, 32, Colors.SKYBLUE, Colors.RAYWHITE): image =>
      require(Images.isValid(image), "Could not generate checker image")

      Textures.withTextureFromImage(image): texture =>
        require(Textures.isValid(texture), "Could not upload checker texture")
        Textures.setFilter(texture, TextureFilters.Point)

        val textureWidth = Textures.width(texture).toFloat
        val textureHeight = Textures.height(texture).toFloat

        while !Window.shouldClose do
          val rotation = ((Time.elapsed * 30.0) % 360.0).toFloat

          Drawing.frame:
            val scale = 1.2f
            val width = textureWidth * scale
            val height = textureHeight * scale
            val source = Rect(0.0f, 0.0f, textureWidth, textureHeight)
            val destination = Rect(Window.screenWidth / 2.0f, Window.screenHeight / 2.0f + 18.0f, width, height)
            val origin = Vector.vector2(width / 2.0f, height / 2.0f)

            Drawing.clear(Colors.DARKGRAY)
            Drawing.text("Generated Image -> GPU Texture", 24, 24, 28, Colors.RAYWHITE)
            Drawing.text(s"${textureWidth.toInt}x${textureHeight.toInt}, id=${Textures.id(texture)}, point filtered", 24, 60, 18, Colors.LIGHTGRAY)
            Textures.drawPro(texture, source, destination, origin, rotation, Colors.WHITE)
