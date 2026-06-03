package rayscal

import scala.scalanative.unsafe.*

object Rect:
  def apply(x: Float, y: Float, width: Float, height: Float)(using zone: Zone): Rectangle =
    val _ = zone
    val value = stackalloc[Rectangle]()
    (!value)._1 = x
    (!value)._2 = y
    (!value)._3 = width
    (!value)._4 = height
    !value

  extension (value: Rectangle)
    def x: Float = value._1
    def y: Float = value._2
    def width: Float = value._3
    def height: Float = value._4
