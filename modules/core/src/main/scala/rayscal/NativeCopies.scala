package rayscal

import scala.scalanative.unsafe.*

private[rayscal] object NativeCopies:
  def color(value: Color)(using zone: Zone): Ptr[Color] =
    val _ = zone
    val out = stackalloc[Color]()
    (!out)._1 = value._1
    (!out)._2 = value._2
    (!out)._3 = value._3
    (!out)._4 = value._4
    out

  def vector2(value: Vector2)(using zone: Zone): Ptr[Vector2] =
    val _ = zone
    val out = stackalloc[Vector2]()
    (!out)._1 = value._1
    (!out)._2 = value._2
    out

  def rectangle(value: Rectangle)(using zone: Zone): Ptr[Rectangle] =
    val _ = zone
    val out = stackalloc[Rectangle]()
    (!out)._1 = value._1
    (!out)._2 = value._2
    (!out)._3 = value._3
    (!out)._4 = value._4
    out

  def texture2D(value: Texture2D)(using zone: Zone): Ptr[Texture2D] =
    val _ = zone
    val out = stackalloc[Texture2D]()
    (!out)._1 = value._1
    (!out)._2 = value._2
    (!out)._3 = value._3
    (!out)._4 = value._4
    (!out)._5 = value._5
    out

  def camera2D(value: Camera2D)(using zone: Zone): Ptr[Camera2D] =
    val _ = zone
    val out = stackalloc[Camera2D]()
    (!out)._1 = value._1
    (!out)._2 = value._2
    (!out)._3 = value._3
    (!out)._4 = value._4
    (!out)._5 = value._5
    (!out)._6 = value._6
    out

  def camera3D(value: Camera3D)(using zone: Zone): Ptr[Camera3D] =
    val _ = zone
    val out = stackalloc[Camera3D]()
    (!out)._1 = value._1
    (!out)._2 = value._2
    (!out)._3 = value._3
    (!out)._4 = value._4
    (!out)._5 = value._5
    (!out)._6 = value._6
    (!out)._7 = value._7
    (!out)._8 = value._8
    (!out)._9 = value._9
    (!out)._10 = value._10
    (!out)._11 = value._11
    out
