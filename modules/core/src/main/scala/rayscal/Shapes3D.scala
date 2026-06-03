package rayscal

import rayscal.raw.Raylib

object Shapes3D:
  def line(start: Vector3, end: Vector3, color: Color): Unit =
    Raylib.DrawLine3D(start, end, color)

  def point(position: Vector3, color: Color): Unit =
    Raylib.DrawPoint3D(position, color)

  def circle(center: Vector3, radius: Float, rotationAxis: Vector3, rotationAngle: Float, color: Color): Unit =
    Raylib.DrawCircle3D(center, radius, rotationAxis, rotationAngle, color)

  def triangle(v1: Vector3, v2: Vector3, v3: Vector3, color: Color): Unit =
    Raylib.DrawTriangle3D(v1, v2, v3, color)

  def cube(position: Vector3, width: Float, height: Float, length: Float, color: Color): Unit =
    Raylib.DrawCube(position, width, height, length, color)

  def cube(position: Vector3, size: Vector3, color: Color): Unit =
    Raylib.DrawCubeV(position, size, color)

  def cubeWires(position: Vector3, width: Float, height: Float, length: Float, color: Color): Unit =
    Raylib.DrawCubeWires(position, width, height, length, color)

  def cubeWires(position: Vector3, size: Vector3, color: Color): Unit =
    Raylib.DrawCubeWiresV(position, size, color)

  def sphere(center: Vector3, radius: Float, color: Color): Unit =
    Raylib.DrawSphere(center, radius, color)

  def sphere(center: Vector3, radius: Float, rings: Int, slices: Int, color: Color): Unit =
    Raylib.DrawSphereEx(center, radius, rings, slices, color)

  def sphereWires(center: Vector3, radius: Float, rings: Int, slices: Int, color: Color): Unit =
    Raylib.DrawSphereWires(center, radius, rings, slices, color)

  def cylinder(position: Vector3, radiusTop: Float, radiusBottom: Float, height: Float, slices: Int, color: Color): Unit =
    Raylib.DrawCylinder(position, radiusTop, radiusBottom, height, slices, color)

  def cylinder(start: Vector3, end: Vector3, startRadius: Float, endRadius: Float, sides: Int, color: Color): Unit =
    Raylib.DrawCylinderEx(start, end, startRadius, endRadius, sides, color)

  def cylinderWires(position: Vector3, radiusTop: Float, radiusBottom: Float, height: Float, slices: Int, color: Color): Unit =
    Raylib.DrawCylinderWires(position, radiusTop, radiusBottom, height, slices, color)

  def capsule(start: Vector3, end: Vector3, radius: Float, slices: Int, rings: Int, color: Color): Unit =
    Raylib.DrawCapsule(start, end, radius, slices, rings, color)

  def capsuleWires(start: Vector3, end: Vector3, radius: Float, slices: Int, rings: Int, color: Color): Unit =
    Raylib.DrawCapsuleWires(start, end, radius, slices, rings, color)

  def plane(center: Vector3, size: Vector2, color: Color): Unit =
    Raylib.DrawPlane(center, size, color)

  def ray(ray: Ray, color: Color): Unit =
    Raylib.DrawRay(ray, color)

  def grid(slices: Int, spacing: Float): Unit =
    Raylib.DrawGrid(slices, spacing)
