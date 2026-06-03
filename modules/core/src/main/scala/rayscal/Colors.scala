package rayscal

import rayscal.raw.Color
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

object Colors:
  def rgba(r: Int, g: Int, b: Int, a: Int)(using zone: Zone): Color =
    // raylib's Color is a by-value C struct of four unsigned bytes.
    // Allocate it in the current Zone, fill the fields, then pass the value
    // across the native boundary.
    val _ = zone
    val color = stackalloc[Color]()
    (!color)._1 = r.toUByte
    (!color)._2 = g.toUByte
    (!color)._3 = b.toUByte
    (!color)._4 = a.toUByte
    !color

  def RAYWHITE(using Zone): Color = rgba(245, 245, 245, 255)
  def LIGHTGRAY(using Zone): Color = rgba(200, 200, 200, 255)
  def GRAY(using Zone): Color = rgba(130, 130, 130, 255)
  def DARKGRAY(using Zone): Color = rgba(80, 80, 80, 255)
  def YELLOW(using Zone): Color = rgba(253, 249, 0, 255)
  def GOLD(using Zone): Color = rgba(255, 203, 0, 255)
  def ORANGE(using Zone): Color = rgba(255, 161, 0, 255)
  def PINK(using Zone): Color = rgba(255, 109, 194, 255)
  def BLACK(using Zone): Color = rgba(0, 0, 0, 255)
  def WHITE(using Zone): Color = rgba(255, 255, 255, 255)
  def RED(using Zone): Color = rgba(230, 41, 55, 255)
  def MAROON(using Zone): Color = rgba(190, 33, 55, 255)
  def GREEN(using Zone): Color = rgba(0, 228, 48, 255)
  def LIME(using Zone): Color = rgba(0, 158, 47, 255)
  def BLUE(using Zone): Color = rgba(0, 121, 241, 255)
  def SKYBLUE(using Zone): Color = rgba(102, 191, 255, 255)
  def DARKBLUE(using Zone): Color = rgba(0, 82, 172, 255)
  def PURPLE(using Zone): Color = rgba(200, 122, 255, 255)
  def VIOLET(using Zone): Color = rgba(135, 60, 190, 255)
  def BEIGE(using Zone): Color = rgba(211, 176, 131, 255)
  def BROWN(using Zone): Color = rgba(127, 106, 79, 255)
  def BLANK(using Zone): Color = rgba(0, 0, 0, 0)
  def MAGENTA(using Zone): Color = rgba(255, 0, 255, 255)

  def fade(color: Color, alpha: Float)(using Zone): Color =
    rgba(color._1.toInt, color._2.toInt, color._3.toInt, math.max(0, math.min(255, (255.0f * alpha).round)))

  def tint(color: Color, tint: Color): Color =
    rayscal.raw.Raylib.ColorTint(color, tint)

  def brightness(color: Color, factor: Float): Color =
    rayscal.raw.Raylib.ColorBrightness(color, factor)

  def fromHSV(hue: Float, saturation: Float, value: Float): Color =
    rayscal.raw.Raylib.ColorFromHSV(hue, saturation, value)

  def toHSV(color: Color): Vector3 =
    rayscal.raw.Raylib.ColorToHSV(color)

  def toInt(color: Color): Int =
    rayscal.raw.Raylib.ColorToInt(color)

  def isEqual(left: Color, right: Color): Boolean =
    rayscal.raw.Raylib.ColorIsEqual(left, right)
