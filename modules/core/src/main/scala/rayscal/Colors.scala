package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.*

object Colors:
  def rgba(r: Int, g: Int, b: Int, a: Int): Color =
    Color(clampByte(r), clampByte(g), clampByte(b), clampByte(a))

  private def clampByte(value: Int): Int =
    math.max(0, math.min(255, value))

  val RAYWHITE: Color = rgba(245, 245, 245, 255)
  val LIGHTGRAY: Color = rgba(200, 200, 200, 255)
  val GRAY: Color = rgba(130, 130, 130, 255)
  val DARKGRAY: Color = rgba(80, 80, 80, 255)
  val YELLOW: Color = rgba(253, 249, 0, 255)
  val GOLD: Color = rgba(255, 203, 0, 255)
  val ORANGE: Color = rgba(255, 161, 0, 255)
  val PINK: Color = rgba(255, 109, 194, 255)
  val BLACK: Color = rgba(0, 0, 0, 255)
  val WHITE: Color = rgba(255, 255, 255, 255)
  val RED: Color = rgba(230, 41, 55, 255)
  val MAROON: Color = rgba(190, 33, 55, 255)
  val GREEN: Color = rgba(0, 228, 48, 255)
  val LIME: Color = rgba(0, 158, 47, 255)
  val BLUE: Color = rgba(0, 121, 241, 255)
  val SKYBLUE: Color = rgba(102, 191, 255, 255)
  val DARKBLUE: Color = rgba(0, 82, 172, 255)
  val PURPLE: Color = rgba(200, 122, 255, 255)
  val VIOLET: Color = rgba(135, 60, 190, 255)
  val BEIGE: Color = rgba(211, 176, 131, 255)
  val BROWN: Color = rgba(127, 106, 79, 255)
  val BLANK: Color = rgba(0, 0, 0, 0)
  val MAGENTA: Color = rgba(255, 0, 255, 255)

  def fade(color: Color, alpha: Float): Color =
    Zone:
      val out = alloc[raw.Color]()
      RayscalNative.Fade(out, NativeMarshal.color(color), alpha)
      NativeMarshal.readColor(out)

  def tint(color: Color, tint: Color): Color =
    Zone:
      val out = alloc[raw.Color]()
      RayscalNative.ColorTint(out, NativeMarshal.color(color), NativeMarshal.color(tint))
      NativeMarshal.readColor(out)

  def brightness(color: Color, factor: Float): Color =
    Zone:
      val out = alloc[raw.Color]()
      RayscalNative.ColorBrightness(out, NativeMarshal.color(color), factor)
      NativeMarshal.readColor(out)

  def fromHSV(hue: Float, saturation: Float, value: Float): Color =
    Zone:
      val out = alloc[raw.Color]()
      RayscalNative.ColorFromHSV(out, hue, saturation, value)
      NativeMarshal.readColor(out)

  def toHSV(color: Color): Vector3 =
    Zone:
      val out = alloc[raw.Vector3]()
      RayscalNative.ColorToHSV(out, NativeMarshal.color(color))
      NativeMarshal.readVector3(out)

  def toInt(color: Color): Int =
    NativeMarshal.withColor(color)(RayscalNative.ColorToInt)

  def isEqual(left: Color, right: Color): Boolean =
    Zone:
      RayscalNative.ColorIsEqual(NativeMarshal.color(left), NativeMarshal.color(right))
