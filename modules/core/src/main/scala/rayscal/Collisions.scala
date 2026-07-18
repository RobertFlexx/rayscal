package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.*

object Bounds:
  def box(minimum: Vector3, maximum: Vector3): BoundingBox =
    Zone:
      val box = stackalloc[BoundingBox]()
      (!box)._1 = minimum._1
      (!box)._2 = minimum._2
      (!box)._3 = minimum._3
      (!box)._4 = maximum._1
      (!box)._5 = maximum._2
      (!box)._6 = maximum._3
      !box

object Collisions:
  def rectangles(a: Rectangle, b: Rectangle): Boolean =
    Zone(RayscalNative.CheckCollisionRecs(NativeCopies.rectangle(a), NativeCopies.rectangle(b)))

  def circles(center1: Vector2, radius1: Float, center2: Vector2, radius2: Float): Boolean =
    Zone(RayscalNative.CheckCollisionCircles(NativeCopies.vector2(center1), radius1, NativeCopies.vector2(center2), radius2))

  def circleRectangle(center: Vector2, radius: Float, rectangle: Rectangle): Boolean =
    Zone(RayscalNative.CheckCollisionCircleRec(NativeCopies.vector2(center), radius, NativeCopies.rectangle(rectangle)))

  def pointRectangle(point: Vector2, rectangle: Rectangle): Boolean =
    Zone(RayscalNative.CheckCollisionPointRec(NativeCopies.vector2(point), NativeCopies.rectangle(rectangle)))

  def pointCircle(point: Vector2, center: Vector2, radius: Float): Boolean =
    Zone(RayscalNative.CheckCollisionPointCircle(NativeCopies.vector2(point), NativeCopies.vector2(center), radius))

  def pointTriangle(point: Vector2, p1: Vector2, p2: Vector2, p3: Vector2): Boolean =
    Zone(RayscalNative.CheckCollisionPointTriangle(NativeCopies.vector2(point), NativeCopies.vector2(p1), NativeCopies.vector2(p2), NativeCopies.vector2(p3)))

  def collisionRectangle(a: Rectangle, b: Rectangle)(using Zone): Rectangle =
    val out = stackalloc[Rectangle]()
    RayscalNative.GetCollisionRec(out, NativeCopies.rectangle(a), NativeCopies.rectangle(b))
    !out

  def spheres(center1: Vector3, radius1: Float, center2: Vector3, radius2: Float): Boolean =
    Zone(RayscalNative.CheckCollisionSpheres(NativeCopies.vector3(center1), radius1, NativeCopies.vector3(center2), radius2))

  def boxes(a: BoundingBox, b: BoundingBox): Boolean =
    Zone(RayscalNative.CheckCollisionBoxes(NativeCopies.boundingBox(a), NativeCopies.boundingBox(b)))

  def boxSphere(box: BoundingBox, center: Vector3, radius: Float): Boolean =
    Zone(RayscalNative.CheckCollisionBoxSphere(NativeCopies.boundingBox(box), NativeCopies.vector3(center), radius))

object RayHits:
  def sphere(ray: Ray, center: Vector3, radius: Float)(using Zone): RayCollision =
    result(out => RayscalNative.GetRayCollisionSphere(out, NativeCopies.ray(ray), NativeCopies.vector3(center), radius))

  def box(ray: Ray, box: BoundingBox)(using Zone): RayCollision =
    result(out => RayscalNative.GetRayCollisionBox(out, NativeCopies.ray(ray), NativeCopies.boundingBox(box)))

  def triangle(ray: Ray, p1: Vector3, p2: Vector3, p3: Vector3)(using Zone): RayCollision =
    result(out => RayscalNative.GetRayCollisionTriangle(out, NativeCopies.ray(ray), NativeCopies.vector3(p1), NativeCopies.vector3(p2), NativeCopies.vector3(p3)))

  def quad(ray: Ray, p1: Vector3, p2: Vector3, p3: Vector3, p4: Vector3)(using Zone): RayCollision =
    result(out => RayscalNative.GetRayCollisionQuad(out, NativeCopies.ray(ray), NativeCopies.vector3(p1), NativeCopies.vector3(p2), NativeCopies.vector3(p3), NativeCopies.vector3(p4)))

  def model(ray: Ray, model: Model)(using Zone): RayCollision =
    model.requireLive()
    result(out => RayscalNative.GetRayCollisionModel(out, NativeCopies.ray(ray), model.ptr))

  def hit(result: RayCollision): Boolean = result._1
  def distance(result: RayCollision): Float = result._2
  def point(result: RayCollision)(using Zone): Vector3 = Vector.vector3(result._3, result._4, result._5)
  def normal(result: RayCollision)(using Zone): Vector3 = Vector.vector3(result._6, result._7, result._8)

  private def result(call: Ptr[RayCollision] => Unit): RayCollision =
    Zone:
      val out = stackalloc[RayCollision]()
      call(out)
      !out
