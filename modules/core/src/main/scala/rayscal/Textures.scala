package rayscal

import rayscal.raw.{Raylib, RayscalNative}
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

object Images:
  def load(fileName: String): Image =
    Zone:
      Raylib.LoadImage(toCString(fileName))

  def checked(width: Int, height: Int, checksX: Int, checksY: Int, color1: Color, color2: Color): Image =
    Raylib.GenImageChecked(width, height, checksX, checksY, color1, color2)

  def solid(width: Int, height: Int, color: Color): Image =
    Raylib.GenImageColor(width, height, color)

  def isValid(image: Image): Boolean =
    Raylib.IsImageValid(image)

  def unload(image: Image): Unit =
    Raylib.UnloadImage(image)

  def withImage(fileName: String)(body: Image => Unit): Unit =
    val image = load(fileName)
    try body(image)
    finally unload(image)

  def withChecked(width: Int, height: Int, checksX: Int, checksY: Int, color1: Color, color2: Color)(body: Image => Unit): Unit =
    val image = checked(width, height, checksX, checksY, color1, color2)
    try body(image)
    finally unload(image)

object Textures:
  def load(fileName: String): Texture2D =
    Zone:
      Raylib.LoadTexture(toCString(fileName))

  def fromImage(image: Image): Texture2D =
    Raylib.LoadTextureFromImage(image)

  def isValid(texture: Texture2D): Boolean =
    Raylib.IsTextureValid(texture)

  def unload(texture: Texture2D): Unit =
    Raylib.UnloadTexture(texture)

  def withTexture(fileName: String)(body: Texture2D => Unit): Unit =
    val texture = load(fileName)
    try body(texture)
    finally unload(texture)

  def withTextureFromImage(image: Image)(body: Texture2D => Unit): Unit =
    val texture = fromImage(image)
    try body(texture)
    finally unload(texture)

  def setFilter(texture: Texture2D, filter: Int): Unit =
    Raylib.SetTextureFilter(texture, filter)

  def setWrap(texture: Texture2D, wrap: Int): Unit =
    Raylib.SetTextureWrap(texture, wrap)

  def draw(texture: Texture2D, x: Int, y: Int, tint: Color): Unit =
    Raylib.DrawTexture(texture, x, y, tint)

  def draw(texture: Texture2D, position: Vector2, tint: Color): Unit =
    Raylib.DrawTextureV(texture, position, tint)

  def draw(texture: Texture2D, position: Vector2, rotation: Float, scale: Float, tint: Color): Unit =
    Raylib.DrawTextureEx(texture, position, rotation, scale, tint)

  def drawRec(texture: Texture2D, source: Rectangle, position: Vector2, tint: Color): Unit =
    Raylib.DrawTextureRec(texture, source, position, tint)

  def drawPro(texture: Texture2D, source: Rectangle, dest: Rectangle, origin: Vector2, rotation: Float, tint: Color): Unit =
    Zone:
      RayscalNative.DrawTexturePro(
        NativeCopies.texture2D(texture),
        NativeCopies.rectangle(source),
        NativeCopies.rectangle(dest),
        NativeCopies.vector2(origin),
        rotation,
        NativeCopies.color(tint)
      )

  def id(texture: Texture2D): Int =
    texture._1.toInt

  def width(texture: Texture2D): Int =
    texture._2

  def height(texture: Texture2D): Int =
    texture._3

  def mipmaps(texture: Texture2D): Int =
    texture._4

  def format(texture: Texture2D): Int =
    texture._5
