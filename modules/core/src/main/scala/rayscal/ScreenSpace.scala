package rayscal

import rayscal.raw.Raylib
import scala.scalanative.unsafe.*

object ScreenSpace:
  def screenToWorldRay(position: Vector2, camera: Camera3D): Ray =
    Raylib.GetScreenToWorldRay(position, camera)

  def screenToWorldRay(position: Vector2, camera: Camera3D, width: Int, height: Int): Ray =
    Raylib.GetScreenToWorldRayEx(position, camera, width, height)

  def worldToScreen(position: Vector3, camera: Camera3D): Vector2 =
    Raylib.GetWorldToScreen(position, camera)

  def worldToScreen(position: Vector3, camera: Camera3D, width: Int, height: Int): Vector2 =
    Raylib.GetWorldToScreenEx(position, camera, width, height)

  def worldToScreen2D(position: Vector2, camera: Camera2D)(using zone: Zone): Vector2 =
    val radians = camera._5.toDouble * math.Pi / 180.0
    val cos = math.cos(radians).toFloat
    val sin = math.sin(radians).toFloat
    val x = position._1 - camera._3
    val y = position._2 - camera._4
    Vector.vector2(
      (x * cos - y * sin) * camera._6 + camera._1,
      (x * sin + y * cos) * camera._6 + camera._2
    )

  def screenToWorld2D(position: Vector2, camera: Camera2D)(using zone: Zone): Vector2 =
    val radians = -camera._5.toDouble * math.Pi / 180.0
    val cos = math.cos(radians).toFloat
    val sin = math.sin(radians).toFloat
    val x = (position._1 - camera._1) / camera._6
    val y = (position._2 - camera._2) / camera._6
    Vector.vector2(
      x * cos - y * sin + camera._3,
      x * sin + y * cos + camera._4
    )
