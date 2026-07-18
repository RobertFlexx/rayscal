package rayscal

import rayscal.raw.{Raylib, RayscalNative}
import scala.scalanative.libc.stdlib
import scala.scalanative.unsafe.*

object TextureFilters:
  val Point: Int = 0
  val Bilinear: Int = 1
  val Trilinear: Int = 2
  val Anisotropic4x: Int = 3
  val Anisotropic8x: Int = 4
  val Anisotropic16x: Int = 5

object TextureWraps:
  val Repeat: Int = 0
  val Clamp: Int = 1
  val MirrorRepeat: Int = 2
  val MirrorClamp: Int = 3

object CubemapLayouts:
  val AutoDetect = 0
  val LineVertical = 1
  val LineHorizontal = 2
  val CrossThreeByFour = 3
  val CrossFourByThree = 4

object Images:
  def load(fileName: String): Image =
    Zone:
      val image = allocate()
      RayscalNative.LoadImage(image.ptr, toCString(fileName))
      image

  def checked(width: Int, height: Int, checksX: Int, checksY: Int, color1: Color, color2: Color): Image =
    Zone:
      val image = allocate()
      RayscalNative.GenImageChecked(image.ptr, width, height, checksX, checksY, NativeCopies.color(color1), NativeCopies.color(color2))
      image

  def solid(width: Int, height: Int, color: Color): Image =
    Zone:
      val image = allocate()
      RayscalNative.GenImageColor(image.ptr, width, height, NativeCopies.color(color))
      image

  def gradientLinear(width: Int, height: Int, direction: Int, start: Color, end: Color): Image =
    Zone:
      val image = allocate()
      RayscalNative.GenImageGradientLinear(image.ptr, width, height, direction, NativeCopies.color(start), NativeCopies.color(end))
      image

  def gradientRadial(width: Int, height: Int, density: Float, inner: Color, outer: Color): Image =
    Zone:
      val image = allocate()
      RayscalNative.GenImageGradientRadial(image.ptr, width, height, density, NativeCopies.color(inner), NativeCopies.color(outer))
      image

  def perlinNoise(width: Int, height: Int, offsetX: Int, offsetY: Int, scale: Float): Image =
    Zone:
      val image = allocate()
      RayscalNative.GenImagePerlinNoise(image.ptr, width, height, offsetX, offsetY, scale)
      image

  def cellular(width: Int, height: Int, tileSize: Int): Image =
    Zone:
      val image = allocate()
      RayscalNative.GenImageCellular(image.ptr, width, height, tileSize)
      image

  def copy(image: Image): Image =
    Zone:
      image.requireLive()
      val out = allocate()
      RayscalNative.ImageCopy(out.ptr, image.ptr)
      out

  def crop(image: Image, crop: Rectangle): Unit =
    Zone:
      image.requireLive()
      RayscalNative.ImageCrop(image.ptr, NativeCopies.rectangle(crop))

  def resize(image: Image, width: Int, height: Int): Unit =
    image.requireLive()
    Raylib.ImageResize(image.ptr, width, height)

  def resizeNearest(image: Image, width: Int, height: Int): Unit =
    image.requireLive()
    Raylib.ImageResizeNN(image.ptr, width, height)

  def mipmaps(image: Image): Unit =
    image.requireLive()
    Raylib.ImageMipmaps(image.ptr)

  def flipVertical(image: Image): Unit =
    image.requireLive()
    Raylib.ImageFlipVertical(image.ptr)

  def flipHorizontal(image: Image): Unit =
    image.requireLive()
    Raylib.ImageFlipHorizontal(image.ptr)

  def rotate(image: Image, degrees: Int): Unit =
    image.requireLive()
    Raylib.ImageRotate(image.ptr, degrees)

  def tint(image: Image, color: Color): Unit =
    Zone:
      image.requireLive()
      RayscalNative.ImageColorTint(image.ptr, NativeCopies.color(color))

  def invert(image: Image): Unit =
    image.requireLive()
    Raylib.ImageColorInvert(image.ptr)

  def grayscale(image: Image): Unit =
    image.requireLive()
    Raylib.ImageColorGrayscale(image.ptr)

  def contrast(image: Image, contrast: Float): Unit =
    image.requireLive()
    Raylib.ImageColorContrast(image.ptr, contrast)

  def brightness(image: Image, brightness: Int): Unit =
    image.requireLive()
    Raylib.ImageColorBrightness(image.ptr, brightness)

  def isValid(image: Image): Boolean =
    image.requireLive()
    RayscalNative.IsImageValid(image.ptr)

  def unload(image: Image): Unit =
    if !image.disposed then
      if RayscalNative.IsImageValid(image.ptr) then RayscalNative.UnloadImage(image.ptr)
      stdlib.free(image.ptr.asInstanceOf[Ptr[Byte]])
      image.disposed = true

  def withImage(fileName: String)(body: Image => Unit): Unit =
    val image = load(fileName)
    try body(image)
    finally unload(image)

  def withChecked(width: Int, height: Int, checksX: Int, checksY: Int, color1: Color, color2: Color)(body: Image => Unit): Unit =
    val image = checked(width, height, checksX, checksY, color1, color2)
    try body(image)
    finally unload(image)

  private def allocate(): Image =
    val ptr = stdlib.malloc(sizeof[raw.Image]).asInstanceOf[Ptr[raw.Image]]
    if ptr == null then throw new OutOfMemoryError("Could not allocate Image")
    new Image(ptr)

object Textures:
  def load(fileName: String): Texture2D =
    Zone:
      val texture = allocate()
      RayscalNative.LoadTexture(texture.ptr, toCString(fileName))
      texture

  def fromImage(image: Image): Texture2D =
    image.requireLive()
    val texture = allocate()
    RayscalNative.LoadTextureFromImage(texture.ptr, image.ptr)
    texture

  def isValid(texture: Texture2D): Boolean =
    texture.requireLive()
    RayscalNative.IsTextureValid(texture.ptr)

  def unload(texture: Texture2D): Unit =
    if !texture.disposed then
      if RayscalNative.IsTextureValid(texture.ptr) then RayscalNative.UnloadTexture(texture.ptr)
      stdlib.free(texture.ptr.asInstanceOf[Ptr[Byte]])
      texture.disposed = true

  def update(texture: Texture2D, pixels: Ptr[Byte]): Unit =
    texture.requireLive()
    RayscalNative.UpdateTexture(texture.ptr, pixels)

  def updateRec(texture: Texture2D, rec: Rectangle, pixels: Ptr[Byte]): Unit =
    Zone:
      texture.requireLive()
      RayscalNative.UpdateTextureRec(texture.ptr, NativeCopies.rectangle(rec), pixels)

  def generateMipmaps(texture: Texture2D): Unit =
    texture.requireLive()
    Raylib.GenTextureMipmaps(texture.ptr)

  def withTexture(fileName: String)(body: Texture2D => Unit): Unit =
    val texture = load(fileName)
    try body(texture)
    finally unload(texture)

  def withTextureFromImage(image: Image)(body: Texture2D => Unit): Unit =
    val texture = fromImage(image)
    try body(texture)
    finally unload(texture)

  def setFilter(texture: Texture2D, filter: Int): Unit =
    texture.requireLive()
    RayscalNative.SetTextureFilter(texture.ptr, filter)

  def setWrap(texture: Texture2D, wrap: Int): Unit =
    texture.requireLive()
    RayscalNative.SetTextureWrap(texture.ptr, wrap)

  def draw(texture: Texture2D, x: Int, y: Int, tint: Color): Unit =
    Zone:
      texture.requireLive()
      RayscalNative.DrawTexture(texture.ptr, x, y, NativeCopies.color(tint))

  def draw(texture: TextureView, x: Int, y: Int, tint: Color): Unit =
    Zone:
      RayscalNative.DrawTexture(texture.native, x, y, NativeCopies.color(tint))

  def draw(texture: Texture2D, position: Vector2, tint: Color): Unit =
    Zone:
      texture.requireLive()
      RayscalNative.DrawTextureV(texture.ptr, NativeCopies.vector2(position), NativeCopies.color(tint))

  def draw(texture: TextureView, position: Vector2, tint: Color): Unit =
    Zone:
      RayscalNative.DrawTextureV(texture.native, NativeCopies.vector2(position), NativeCopies.color(tint))

  def draw(texture: Texture2D, position: Vector2, rotation: Float, scale: Float, tint: Color): Unit =
    Zone:
      texture.requireLive()
      RayscalNative.DrawTextureEx(texture.ptr, NativeCopies.vector2(position), rotation, scale, NativeCopies.color(tint))

  def drawRec(texture: Texture2D, source: Rectangle, position: Vector2, tint: Color): Unit =
    Zone:
      texture.requireLive()
      RayscalNative.DrawTextureRec(texture.ptr, NativeCopies.rectangle(source), NativeCopies.vector2(position), NativeCopies.color(tint))

  def drawRec(texture: TextureView, source: Rectangle, position: Vector2, tint: Color): Unit =
    Zone:
      RayscalNative.DrawTextureRec(texture.native, NativeCopies.rectangle(source), NativeCopies.vector2(position), NativeCopies.color(tint))

  def drawPro(texture: Texture2D, source: Rectangle, dest: Rectangle, origin: Vector2, rotation: Float, tint: Color): Unit =
    Zone:
      texture.requireLive()
      RayscalNative.DrawTexturePro(
        texture.ptr,
        NativeCopies.rectangle(source),
        NativeCopies.rectangle(dest),
        NativeCopies.vector2(origin),
        rotation,
        NativeCopies.color(tint)
      )

  def drawPro(texture: TextureView, source: Rectangle, dest: Rectangle, origin: Vector2, rotation: Float, tint: Color): Unit =
    Zone:
      RayscalNative.DrawTexturePro(
        texture.native,
        NativeCopies.rectangle(source),
        NativeCopies.rectangle(dest),
        NativeCopies.vector2(origin),
        rotation,
        NativeCopies.color(tint)
      )

  def id(texture: Texture2D): Int =
    texture.requireLive()
    (!texture.ptr)._1.toInt

  def id(texture: TextureView): Int =
    Zone((!texture.native)._1.toInt)

  def width(texture: Texture2D): Int =
    texture.requireLive()
    (!texture.ptr)._2

  def width(texture: TextureView): Int =
    Zone((!texture.native)._2)

  def height(texture: Texture2D): Int =
    texture.requireLive()
    (!texture.ptr)._3

  def height(texture: TextureView): Int =
    Zone((!texture.native)._3)

  def mipmaps(texture: Texture2D): Int =
    texture.requireLive()
    (!texture.ptr)._4

  def format(texture: Texture2D): Int =
    texture.requireLive()
    (!texture.ptr)._5

  def format(texture: TextureView): Int =
    Zone((!texture.native)._5)

  private def allocate(): Texture2D =
    val ptr = stdlib.malloc(sizeof[raw.Texture2D]).asInstanceOf[Ptr[raw.Texture2D]]
    if ptr == null then throw new OutOfMemoryError("Could not allocate Texture2D")
    new Texture2D(ptr)

object Cubemaps:
  def fromImage(image: Image, layout: Int = CubemapLayouts.AutoDetect): TextureCubemap =
    image.requireLive()
    val ptr = stdlib.malloc(sizeof[raw.TextureCubemap]).asInstanceOf[Ptr[raw.TextureCubemap]]
    if ptr == null then throw new OutOfMemoryError("Could not allocate TextureCubemap")
    val cubemap = new TextureCubemap(ptr)
    RayscalNative.LoadTextureCubemap(ptr, image.ptr, layout)
    if !isValid(cubemap) then
      unload(cubemap)
      throw new IllegalArgumentException("Could not create cubemap from image")
    cubemap

  def isValid(cubemap: TextureCubemap): Boolean =
    cubemap.requireLive()
    RayscalNative.IsTextureValid(cubemap.ptr)

  def unload(cubemap: TextureCubemap): Unit =
    if !cubemap.disposed then
      if RayscalNative.IsTextureValid(cubemap.ptr) then RayscalNative.UnloadTexture(cubemap.ptr)
      stdlib.free(cubemap.ptr.asInstanceOf[Ptr[Byte]])
      cubemap.disposed = true

  def withCubemap(image: Image, layout: Int = CubemapLayouts.AutoDetect)(body: TextureCubemap => Unit): Unit =
    val cubemap = fromImage(image, layout)
    try body(cubemap)
    finally unload(cubemap)

  def id(cubemap: TextureCubemap): Int =
    cubemap.requireLive()
    (!cubemap.ptr)._1.toInt
