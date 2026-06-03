package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.Zone

object Shapes:
  def pixel(x: Int, y: Int, color: Color): Unit =
    line(x, y, x, y, color)

  def line(x1: Int, y1: Int, x2: Int, y2: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawLine(x1, y1, x2, y2, NativeCopies.color(color))

  def line(start: Vector2, end: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawLineV(NativeCopies.vector2(start), NativeCopies.vector2(end), NativeCopies.color(color))

  def line(start: Vector2, end: Vector2, thick: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawLineEx(NativeCopies.vector2(start), NativeCopies.vector2(end), thick, NativeCopies.color(color))

  def circle(x: Int, y: Int, radius: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCircle(x, y, radius, NativeCopies.color(color))

  def circle(center: Vector2, radius: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCircleV(NativeCopies.vector2(center), radius, NativeCopies.color(color))

  def circleLines(x: Int, y: Int, radius: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCircleLines(x, y, radius, NativeCopies.color(color))

  def ellipse(x: Int, y: Int, radiusH: Float, radiusV: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawEllipse(x, y, radiusH, radiusV, NativeCopies.color(color))

  def ellipseLines(x: Int, y: Int, radiusH: Float, radiusV: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawEllipseLines(x, y, radiusH, radiusV, NativeCopies.color(color))

  def ring(center: Vector2, innerRadius: Float, outerRadius: Float, startAngle: Float, endAngle: Float, segments: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawRing(NativeCopies.vector2(center), innerRadius, outerRadius, startAngle, endAngle, segments, NativeCopies.color(color))

  def rectangle(x: Int, y: Int, width: Int, height: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawRectangle(x, y, width, height, NativeCopies.color(color))

  def rectangle(position: Vector2, size: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawRectangleV(NativeCopies.vector2(position), NativeCopies.vector2(size), NativeCopies.color(color))

  def rectangle(rec: Rectangle, color: Color): Unit =
    Zone:
      RayscalNative.DrawRectangleRec(NativeCopies.rectangle(rec), NativeCopies.color(color))

  def rectangleLines(x: Int, y: Int, width: Int, height: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawRectangleLines(x, y, width, height, NativeCopies.color(color))

  def rectangleLines(rec: Rectangle, thick: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawRectangleLinesEx(NativeCopies.rectangle(rec), thick, NativeCopies.color(color))

  def triangle(v1: Vector2, v2: Vector2, v3: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawTriangle(NativeCopies.vector2(v1), NativeCopies.vector2(v2), NativeCopies.vector2(v3), NativeCopies.color(color))

  def triangleLines(v1: Vector2, v2: Vector2, v3: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawTriangleLines(NativeCopies.vector2(v1), NativeCopies.vector2(v2), NativeCopies.vector2(v3), NativeCopies.color(color))

  def polygon(center: Vector2, sides: Int, radius: Float, rotation: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawPoly(NativeCopies.vector2(center), sides, radius, rotation, NativeCopies.color(color))

  def polygonLines(center: Vector2, sides: Int, radius: Float, rotation: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawPolyLines(NativeCopies.vector2(center), sides, radius, rotation, NativeCopies.color(color))

  def polygonLines(center: Vector2, sides: Int, radius: Float, rotation: Float, thick: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawPolyLinesEx(NativeCopies.vector2(center), sides, radius, rotation, thick, NativeCopies.color(color))
