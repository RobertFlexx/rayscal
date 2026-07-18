package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.*

object Fonts:
  def load(fileName: String, size: Int = 32): Font =
    require(size > 0, "Font size must be positive")
    val ptr = Zone(RayscalNative.LoadFont(toCString(fileName), size))
    if ptr == null then throw new OutOfMemoryError("Could not allocate Font")
    val font = new Font(ptr)
    if !isValid(font) then
      unload(font)
      throw new IllegalArgumentException(s"Could not load font: $fileName")
    font

  def isValid(font: Font): Boolean =
    font.requireLive()
    RayscalNative.IsFontValid(font.ptr)

  def unload(font: Font): Unit =
    if !font.disposed then
      RayscalNative.UnloadFont(font.ptr)
      font.disposed = true

  def withFont(fileName: String, size: Int = 32)(body: Font => Unit): Unit =
    val font = load(fileName, size)
    try body(font)
    finally unload(font)

  def draw(font: Font, text: String, position: Vector2, size: Float, spacing: Float, color: Color)(using Zone): Unit =
    font.requireLive()
    RayscalNative.DrawTextEx(font.ptr, toCString(text), NativeCopies.vector2(position), size, spacing, NativeCopies.color(color))

  def measure(font: Font, text: String, size: Float, spacing: Float = 1.0f)(using Zone): Vector2 =
    font.requireLive()
    val out = stackalloc[Vector2]()
    RayscalNative.MeasureTextEx(out, font.ptr, toCString(text), size, spacing)
    !out
