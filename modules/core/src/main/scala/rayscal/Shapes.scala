package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.Zone

object Shapes:
  def pixel(x: Int, y: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawPixel(x, y, NativeMarshal.color(color))

  def pixel(position: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawPixelV(NativeMarshal.vector2(position), NativeMarshal.color(color))

  def line(x1: Int, y1: Int, x2: Int, y2: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawLine(x1, y1, x2, y2, NativeMarshal.color(color))

  def line(start: Vector2, end: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawLineV(NativeMarshal.vector2(start), NativeMarshal.vector2(end), NativeMarshal.color(color))

  def line(start: Vector2, end: Vector2, thick: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawLineEx(NativeMarshal.vector2(start), NativeMarshal.vector2(end), thick, NativeMarshal.color(color))

  def circle(x: Int, y: Int, radius: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCircle(x, y, radius, NativeMarshal.color(color))

  def circle(center: Vector2, radius: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCircleV(NativeMarshal.vector2(center), radius, NativeMarshal.color(color))

  def circleLines(x: Int, y: Int, radius: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCircleLines(x, y, radius, NativeMarshal.color(color))

  def ellipse(x: Int, y: Int, radiusH: Float, radiusV: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawEllipse(x, y, radiusH, radiusV, NativeMarshal.color(color))

  def ellipseLines(x: Int, y: Int, radiusH: Float, radiusV: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawEllipseLines(x, y, radiusH, radiusV, NativeMarshal.color(color))

  def ring(center: Vector2, innerRadius: Float, outerRadius: Float, startAngle: Float, endAngle: Float, segments: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawRing(NativeMarshal.vector2(center), innerRadius, outerRadius, startAngle, endAngle, segments, NativeMarshal.color(color))

  def rectangle(x: Int, y: Int, width: Int, height: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawRectangle(x, y, width, height, NativeMarshal.color(color))

  def rectangle(position: Vector2, size: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawRectangleV(NativeMarshal.vector2(position), NativeMarshal.vector2(size), NativeMarshal.color(color))

  def rectangle(rec: Rectangle, color: Color): Unit =
    Zone:
      RayscalNative.DrawRectangleRec(NativeMarshal.rectangle(rec), NativeMarshal.color(color))

  def rectangleLines(x: Int, y: Int, width: Int, height: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawRectangleLines(x, y, width, height, NativeMarshal.color(color))

  def rectangleLines(rec: Rectangle, thick: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawRectangleLinesEx(NativeMarshal.rectangle(rec), thick, NativeMarshal.color(color))

  def triangle(v1: Vector2, v2: Vector2, v3: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawTriangle(NativeMarshal.vector2(v1), NativeMarshal.vector2(v2), NativeMarshal.vector2(v3), NativeMarshal.color(color))

  def triangleLines(v1: Vector2, v2: Vector2, v3: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawTriangleLines(NativeMarshal.vector2(v1), NativeMarshal.vector2(v2), NativeMarshal.vector2(v3), NativeMarshal.color(color))

  def polygon(center: Vector2, sides: Int, radius: Float, rotation: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawPoly(NativeMarshal.vector2(center), sides, radius, rotation, NativeMarshal.color(color))

  def polygonLines(center: Vector2, sides: Int, radius: Float, rotation: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawPolyLines(NativeMarshal.vector2(center), sides, radius, rotation, NativeMarshal.color(color))

  def polygonLines(center: Vector2, sides: Int, radius: Float, rotation: Float, thick: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawPolyLinesEx(NativeMarshal.vector2(center), sides, radius, rotation, thick, NativeMarshal.color(color))
