package rayscal

import rayscal.raw.{Raylib, RayscalNative}
import scala.scalanative.unsafe.Zone

object Shapes3D:
  def line(start: Vector3, end: Vector3, color: Color): Unit =
    Zone:
      RayscalNative.DrawLine3D(NativeCopies.vector3(start), NativeCopies.vector3(end), NativeCopies.color(color))

  def point(position: Vector3, color: Color): Unit =
    Zone:
      RayscalNative.DrawPoint3D(NativeCopies.vector3(position), NativeCopies.color(color))

  def circle(center: Vector3, radius: Float, rotationAxis: Vector3, rotationAngle: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCircle3D(NativeCopies.vector3(center), radius, NativeCopies.vector3(rotationAxis), rotationAngle, NativeCopies.color(color))

  def triangle(v1: Vector3, v2: Vector3, v3: Vector3, color: Color): Unit =
    Zone:
      RayscalNative.DrawTriangle3D(NativeCopies.vector3(v1), NativeCopies.vector3(v2), NativeCopies.vector3(v3), NativeCopies.color(color))

  def cube(position: Vector3, width: Float, height: Float, length: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCube(NativeCopies.vector3(position), width, height, length, NativeCopies.color(color))

  def cube(position: Vector3, size: Vector3, color: Color): Unit =
    Zone:
      RayscalNative.DrawCubeV(NativeCopies.vector3(position), NativeCopies.vector3(size), NativeCopies.color(color))

  def cubeWires(position: Vector3, width: Float, height: Float, length: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCubeWires(NativeCopies.vector3(position), width, height, length, NativeCopies.color(color))

  def cubeWires(position: Vector3, size: Vector3, color: Color): Unit =
    Zone:
      RayscalNative.DrawCubeWiresV(NativeCopies.vector3(position), NativeCopies.vector3(size), NativeCopies.color(color))

  def sphere(center: Vector3, radius: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawSphere(NativeCopies.vector3(center), radius, NativeCopies.color(color))

  def sphere(center: Vector3, radius: Float, rings: Int, slices: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawSphereEx(NativeCopies.vector3(center), radius, rings, slices, NativeCopies.color(color))

  def sphereWires(center: Vector3, radius: Float, rings: Int, slices: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawSphereWires(NativeCopies.vector3(center), radius, rings, slices, NativeCopies.color(color))

  def cylinder(position: Vector3, radiusTop: Float, radiusBottom: Float, height: Float, slices: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCylinder(NativeCopies.vector3(position), radiusTop, radiusBottom, height, slices, NativeCopies.color(color))

  def cylinder(start: Vector3, end: Vector3, startRadius: Float, endRadius: Float, sides: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCylinderEx(NativeCopies.vector3(start), NativeCopies.vector3(end), startRadius, endRadius, sides, NativeCopies.color(color))

  def cylinderWires(position: Vector3, radiusTop: Float, radiusBottom: Float, height: Float, slices: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCylinderWires(NativeCopies.vector3(position), radiusTop, radiusBottom, height, slices, NativeCopies.color(color))

  def cylinderWires(start: Vector3, end: Vector3, startRadius: Float, endRadius: Float, sides: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCylinderWiresEx(NativeCopies.vector3(start), NativeCopies.vector3(end), startRadius, endRadius, sides, NativeCopies.color(color))

  def capsule(start: Vector3, end: Vector3, radius: Float, slices: Int, rings: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCapsule(NativeCopies.vector3(start), NativeCopies.vector3(end), radius, slices, rings, NativeCopies.color(color))

  def capsuleWires(start: Vector3, end: Vector3, radius: Float, slices: Int, rings: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCapsuleWires(NativeCopies.vector3(start), NativeCopies.vector3(end), radius, slices, rings, NativeCopies.color(color))

  def plane(center: Vector3, size: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawPlane(NativeCopies.vector3(center), NativeCopies.vector2(size), NativeCopies.color(color))

  def ray(ray: Ray, color: Color): Unit =
    Zone:
      RayscalNative.DrawRay(NativeCopies.ray(ray), NativeCopies.color(color))

  def boundingBox(box: BoundingBox, color: Color): Unit =
    Zone:
      RayscalNative.DrawBoundingBox(NativeCopies.boundingBox(box), NativeCopies.color(color))

  def grid(slices: Int, spacing: Float): Unit =
    Raylib.DrawGrid(slices, spacing)
