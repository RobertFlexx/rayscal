package rayscal

import rayscal.raw.Raylib

object Collisions:
  def rectangles(a: Rectangle, b: Rectangle): Boolean =
    Raylib.CheckCollisionRecs(a, b)

  def circles(center1: Vector2, radius1: Float, center2: Vector2, radius2: Float): Boolean =
    Raylib.CheckCollisionCircles(center1, radius1, center2, radius2)

  def circleRectangle(center: Vector2, radius: Float, rectangle: Rectangle): Boolean =
    Raylib.CheckCollisionCircleRec(center, radius, rectangle)

  def pointRectangle(point: Vector2, rectangle: Rectangle): Boolean =
    Raylib.CheckCollisionPointRec(point, rectangle)

  def pointCircle(point: Vector2, center: Vector2, radius: Float): Boolean =
    Raylib.CheckCollisionPointCircle(point, center, radius)

  def pointTriangle(point: Vector2, p1: Vector2, p2: Vector2, p3: Vector2): Boolean =
    Raylib.CheckCollisionPointTriangle(point, p1, p2, p3)

  def collisionRectangle(a: Rectangle, b: Rectangle): Rectangle =
    Raylib.GetCollisionRec(a, b)

  def spheres(center1: Vector3, radius1: Float, center2: Vector3, radius2: Float): Boolean =
    Raylib.CheckCollisionSpheres(center1, radius1, center2, radius2)

  def boxes(a: BoundingBox, b: BoundingBox): Boolean =
    Raylib.CheckCollisionBoxes(a, b)

  def boxSphere(box: BoundingBox, center: Vector3, radius: Float): Boolean =
    Raylib.CheckCollisionBoxSphere(box, center, radius)
