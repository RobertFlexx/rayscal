package rayscal

import rayscal.raw.{Raylib, RayscalNative}
import scala.scalanative.unsafe.*

object CameraProjection:
  val Perspective: Int = 0
  val Orthographic: Int = 1

object CameraModes:
  val Custom: Int = 0
  val Free: Int = 1
  val Orbital: Int = 2
  val FirstPerson: Int = 3
  val ThirdPerson: Int = 4

object Cameras:
  def camera3D(position: Vector3, target: Vector3, up: Vector3, fovy: Float, projection: Int)(using zone: Zone): Camera3D =
    val _ = zone
    val camera = stackalloc[Camera3D]()
    (!camera)._1 = position._1
    (!camera)._2 = position._2
    (!camera)._3 = position._3
    (!camera)._4 = target._1
    (!camera)._5 = target._2
    (!camera)._6 = target._3
    (!camera)._7 = up._1
    (!camera)._8 = up._2
    (!camera)._9 = up._3
    (!camera)._10 = fovy
    (!camera)._11 = projection
    !camera

  def camera2D(offset: Vector2, target: Vector2, rotation: Float, zoom: Float)(using zone: Zone): Camera2D =
    val _ = zone
    val camera = stackalloc[Camera2D]()
    (!camera)._1 = offset._1
    (!camera)._2 = offset._2
    (!camera)._3 = target._1
    (!camera)._4 = target._2
    (!camera)._5 = rotation
    (!camera)._6 = zoom
    !camera

  def update(camera: Ptr[Camera3D], mode: Int): Unit =
    Raylib.UpdateCamera(camera, mode)

  def updatePro(camera: Ptr[Camera3D], movement: Vector3, rotation: Vector3, zoom: Float): Unit =
    Zone:
      RayscalNative.UpdateCameraPro(camera, NativeCopies.vector3(movement), NativeCopies.vector3(rotation), zoom)
