package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.*

object ScreenSpace:
  def screenToWorldRay(position: Vector2, camera: Camera3D)(using Zone): Ray =
    val out = stackalloc[Ray]()
    RayscalNative.GetScreenToWorldRay(out, NativeCopies.vector2(position), NativeCopies.camera3D(camera))
    !out

  def screenToWorldRay(position: Vector2, camera: Camera3D, width: Int, height: Int)(using Zone): Ray =
    val out = stackalloc[Ray]()
    RayscalNative.GetScreenToWorldRayEx(out, NativeCopies.vector2(position), NativeCopies.camera3D(camera), width, height)
    !out

  def worldToScreen(position: Vector3, camera: Camera3D)(using Zone): Vector2 =
    val out = stackalloc[Vector2]()
    RayscalNative.GetWorldToScreen(out, NativeCopies.vector3(position), NativeCopies.camera3D(camera))
    !out

  def worldToScreen(position: Vector3, camera: Camera3D, width: Int, height: Int)(using Zone): Vector2 =
    val out = stackalloc[Vector2]()
    RayscalNative.GetWorldToScreenEx(out, NativeCopies.vector3(position), NativeCopies.camera3D(camera), width, height)
    !out

  def worldToScreen2D(position: Vector2, camera: Camera2D)(using Zone): Vector2 =
    val out = stackalloc[Vector2]()
    RayscalNative.GetWorldToScreen2D(out, NativeCopies.vector2(position), NativeCopies.camera2D(camera))
    !out

  def screenToWorld2D(position: Vector2, camera: Camera2D)(using Zone): Vector2 =
    val out = stackalloc[Vector2]()
    RayscalNative.GetScreenToWorld2D(out, NativeCopies.vector2(position), NativeCopies.camera2D(camera))
    !out
