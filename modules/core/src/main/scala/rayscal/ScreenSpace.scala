package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.*

object ScreenSpace:
  def screenToWorldRay(position: Vector2, camera: Camera3D): Ray =
    Zone:
      val out = alloc[raw.Ray]()
      RayscalNative.GetScreenToWorldRay(out, NativeMarshal.vector2(position), NativeMarshal.camera3D(camera))
      NativeMarshal.readRay(out)

  def screenToWorldRay(position: Vector2, camera: Camera3D, width: Int, height: Int): Ray =
    Zone:
      val out = alloc[raw.Ray]()
      RayscalNative.GetScreenToWorldRayEx(out, NativeMarshal.vector2(position), NativeMarshal.camera3D(camera), width, height)
      NativeMarshal.readRay(out)

  def worldToScreen(position: Vector3, camera: Camera3D): Vector2 =
    Zone:
      val out = alloc[raw.Vector2]()
      RayscalNative.GetWorldToScreen(out, NativeMarshal.vector3(position), NativeMarshal.camera3D(camera))
      NativeMarshal.readVector2(out)

  def worldToScreen(position: Vector3, camera: Camera3D, width: Int, height: Int): Vector2 =
    Zone:
      val out = alloc[raw.Vector2]()
      RayscalNative.GetWorldToScreenEx(out, NativeMarshal.vector3(position), NativeMarshal.camera3D(camera), width, height)
      NativeMarshal.readVector2(out)

  def worldToScreen2D(position: Vector2, camera: Camera2D): Vector2 =
    Zone:
      val out = alloc[raw.Vector2]()
      RayscalNative.GetWorldToScreen2D(out, NativeMarshal.vector2(position), NativeMarshal.camera2D(camera))
      NativeMarshal.readVector2(out)

  def screenToWorld2D(position: Vector2, camera: Camera2D): Vector2 =
    Zone:
      val out = alloc[raw.Vector2]()
      RayscalNative.GetScreenToWorld2D(out, NativeMarshal.vector2(position), NativeMarshal.camera2D(camera))
      NativeMarshal.readVector2(out)
