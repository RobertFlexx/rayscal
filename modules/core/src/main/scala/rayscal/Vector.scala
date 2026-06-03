package rayscal

import rayscal.raw.Vector2
import scala.scalanative.unsafe.*

object Vector:
  def vector2(x: Float, y: Float)(using zone: Zone): Vector2 =
    val _ = zone
    val value = stackalloc[Vector2]()
    (!value)._1 = x
    (!value)._2 = y
    !value

  def vector3(x: Float, y: Float, z: Float)(using zone: Zone): Vector3 =
    val _ = zone
    val value = stackalloc[Vector3]()
    (!value)._1 = x
    (!value)._2 = y
    (!value)._3 = z
    !value

  extension (value: Vector2)
    def x: Float = value._1
    def y: Float = value._2

  extension (value: Vector3)
    def x: Float = value._1
    def y: Float = value._2
    def z: Float = value._3
