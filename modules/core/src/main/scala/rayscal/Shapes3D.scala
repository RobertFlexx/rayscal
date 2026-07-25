package rayscal

import rayscal.raw.{Raylib, RayscalNative}
import scala.scalanative.unsafe.Zone

object Shapes3D:
  def line(start: Vector3, end: Vector3, color: Color): Unit =
    Zone:
      RayscalNative.DrawLine3D(NativeMarshal.vector3(start), NativeMarshal.vector3(end), NativeMarshal.color(color))

  def point(position: Vector3, color: Color): Unit =
    Zone:
      RayscalNative.DrawPoint3D(NativeMarshal.vector3(position), NativeMarshal.color(color))

  def circle(center: Vector3, radius: Float, rotationAxis: Vector3, rotationAngle: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCircle3D(NativeMarshal.vector3(center), radius, NativeMarshal.vector3(rotationAxis), rotationAngle, NativeMarshal.color(color))

  def triangle(v1: Vector3, v2: Vector3, v3: Vector3, color: Color): Unit =
    Zone:
      RayscalNative.DrawTriangle3D(NativeMarshal.vector3(v1), NativeMarshal.vector3(v2), NativeMarshal.vector3(v3), NativeMarshal.color(color))

  def cube(position: Vector3, width: Float, height: Float, length: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCube(NativeMarshal.vector3(position), width, height, length, NativeMarshal.color(color))

  def cube(position: Vector3, size: Vector3, color: Color): Unit =
    Zone:
      RayscalNative.DrawCubeV(NativeMarshal.vector3(position), NativeMarshal.vector3(size), NativeMarshal.color(color))

  def cubeWires(position: Vector3, width: Float, height: Float, length: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawCubeWires(NativeMarshal.vector3(position), width, height, length, NativeMarshal.color(color))

  def cubeWires(position: Vector3, size: Vector3, color: Color): Unit =
    Zone:
      RayscalNative.DrawCubeWiresV(NativeMarshal.vector3(position), NativeMarshal.vector3(size), NativeMarshal.color(color))

  def sphere(center: Vector3, radius: Float, color: Color): Unit =
    Zone:
      RayscalNative.DrawSphere(NativeMarshal.vector3(center), radius, NativeMarshal.color(color))

  def sphere(center: Vector3, radius: Float, rings: Int, slices: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawSphereEx(NativeMarshal.vector3(center), radius, rings, slices, NativeMarshal.color(color))

  def sphereWires(center: Vector3, radius: Float, rings: Int, slices: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawSphereWires(NativeMarshal.vector3(center), radius, rings, slices, NativeMarshal.color(color))

  def cylinder(position: Vector3, radiusTop: Float, radiusBottom: Float, height: Float, slices: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCylinder(NativeMarshal.vector3(position), radiusTop, radiusBottom, height, slices, NativeMarshal.color(color))

  def cylinder(start: Vector3, end: Vector3, startRadius: Float, endRadius: Float, sides: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCylinderEx(NativeMarshal.vector3(start), NativeMarshal.vector3(end), startRadius, endRadius, sides, NativeMarshal.color(color))

  def cylinderWires(position: Vector3, radiusTop: Float, radiusBottom: Float, height: Float, slices: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCylinderWires(NativeMarshal.vector3(position), radiusTop, radiusBottom, height, slices, NativeMarshal.color(color))

  def cylinderWires(start: Vector3, end: Vector3, startRadius: Float, endRadius: Float, sides: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCylinderWiresEx(NativeMarshal.vector3(start), NativeMarshal.vector3(end), startRadius, endRadius, sides, NativeMarshal.color(color))

  def capsule(start: Vector3, end: Vector3, radius: Float, slices: Int, rings: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCapsule(NativeMarshal.vector3(start), NativeMarshal.vector3(end), radius, slices, rings, NativeMarshal.color(color))

  def capsuleWires(start: Vector3, end: Vector3, radius: Float, slices: Int, rings: Int, color: Color): Unit =
    Zone:
      RayscalNative.DrawCapsuleWires(NativeMarshal.vector3(start), NativeMarshal.vector3(end), radius, slices, rings, NativeMarshal.color(color))

  def plane(center: Vector3, size: Vector2, color: Color): Unit =
    Zone:
      RayscalNative.DrawPlane(NativeMarshal.vector3(center), NativeMarshal.vector2(size), NativeMarshal.color(color))

  def ray(ray: Ray, color: Color): Unit =
    Zone:
      RayscalNative.DrawRay(NativeMarshal.ray(ray), NativeMarshal.color(color))

  def boundingBox(box: BoundingBox, color: Color): Unit =
    Zone:
      RayscalNative.DrawBoundingBox(NativeMarshal.boundingBox(box), NativeMarshal.color(color))

  def grid(slices: Int, spacing: Float): Unit =
    Raylib.DrawGrid(slices, spacing)
