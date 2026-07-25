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
  def camera3D(position: Vector3, target: Vector3, up: Vector3, fovy: Float, projection: Int): Camera3D =
    Camera3D(position, target, up, fovy, projection)

  def camera2D(offset: Vector2, target: Vector2, rotation: Float, zoom: Float): Camera2D =
    Camera2D(offset, target, rotation, zoom)

  /** Updates a camera with raylib's built-in control mode and returns the new value. */
  def update(camera: Camera3D, mode: Int): Camera3D =
    Zone:
      val native = alloc[raw.Camera3D]()
      NativeMarshal.writeCamera3D(native, camera)
      Raylib.UpdateCamera(native, mode)
      NativeMarshal.readCamera3D(native)

  def updatePro(camera: Camera3D, movement: Vector3, rotation: Vector3, zoom: Float): Camera3D =
    Zone:
      val native = alloc[raw.Camera3D]()
      NativeMarshal.writeCamera3D(native, camera)
      RayscalNative.UpdateCameraPro(native, NativeMarshal.vector3(movement), NativeMarshal.vector3(rotation), zoom)
      NativeMarshal.readCamera3D(native)
