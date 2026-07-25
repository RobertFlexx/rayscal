package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.*

object Bounds:
  def box(minimum: Vector3, maximum: Vector3): BoundingBox =
    BoundingBox(minimum, maximum)

object Collisions:
  def rectangles(a: Rectangle, b: Rectangle): Boolean =
    Zone(RayscalNative.CheckCollisionRecs(NativeMarshal.rectangle(a), NativeMarshal.rectangle(b)))

  def circles(center1: Vector2, radius1: Float, center2: Vector2, radius2: Float): Boolean =
    Zone(RayscalNative.CheckCollisionCircles(NativeMarshal.vector2(center1), radius1, NativeMarshal.vector2(center2), radius2))

  def circleRectangle(center: Vector2, radius: Float, rectangle: Rectangle): Boolean =
    Zone(RayscalNative.CheckCollisionCircleRec(NativeMarshal.vector2(center), radius, NativeMarshal.rectangle(rectangle)))

  def pointRectangle(point: Vector2, rectangle: Rectangle): Boolean =
    Zone(RayscalNative.CheckCollisionPointRec(NativeMarshal.vector2(point), NativeMarshal.rectangle(rectangle)))

  def pointCircle(point: Vector2, center: Vector2, radius: Float): Boolean =
    Zone(RayscalNative.CheckCollisionPointCircle(NativeMarshal.vector2(point), NativeMarshal.vector2(center), radius))

  def pointTriangle(point: Vector2, p1: Vector2, p2: Vector2, p3: Vector2): Boolean =
    Zone(RayscalNative.CheckCollisionPointTriangle(NativeMarshal.vector2(point), NativeMarshal.vector2(p1), NativeMarshal.vector2(p2), NativeMarshal.vector2(p3)))

  def collisionRectangle(a: Rectangle, b: Rectangle): Rectangle =
    Zone:
      val out = alloc[raw.Rectangle]()
      RayscalNative.GetCollisionRec(out, NativeMarshal.rectangle(a), NativeMarshal.rectangle(b))
      NativeMarshal.readRectangle(out)

  def spheres(center1: Vector3, radius1: Float, center2: Vector3, radius2: Float): Boolean =
    Zone(RayscalNative.CheckCollisionSpheres(NativeMarshal.vector3(center1), radius1, NativeMarshal.vector3(center2), radius2))

  def boxes(a: BoundingBox, b: BoundingBox): Boolean =
    Zone(RayscalNative.CheckCollisionBoxes(NativeMarshal.boundingBox(a), NativeMarshal.boundingBox(b)))

  def boxSphere(box: BoundingBox, center: Vector3, radius: Float): Boolean =
    Zone(RayscalNative.CheckCollisionBoxSphere(NativeMarshal.boundingBox(box), NativeMarshal.vector3(center), radius))

object RayHits:
  def sphere(ray: Ray, center: Vector3, radius: Float): RayCollision =
    Zone:
      val out = alloc[raw.RayCollision]()
      RayscalNative.GetRayCollisionSphere(out, NativeMarshal.ray(ray), NativeMarshal.vector3(center), radius)
      NativeMarshal.readRayCollision(out)

  def box(ray: Ray, box: BoundingBox): RayCollision =
    Zone:
      val out = alloc[raw.RayCollision]()
      RayscalNative.GetRayCollisionBox(out, NativeMarshal.ray(ray), NativeMarshal.boundingBox(box))
      NativeMarshal.readRayCollision(out)

  def triangle(ray: Ray, p1: Vector3, p2: Vector3, p3: Vector3): RayCollision =
    Zone:
      val out = alloc[raw.RayCollision]()
      RayscalNative.GetRayCollisionTriangle(out, NativeMarshal.ray(ray), NativeMarshal.vector3(p1), NativeMarshal.vector3(p2), NativeMarshal.vector3(p3))
      NativeMarshal.readRayCollision(out)

  def quad(ray: Ray, p1: Vector3, p2: Vector3, p3: Vector3, p4: Vector3): RayCollision =
    Zone:
      val out = alloc[raw.RayCollision]()
      RayscalNative.GetRayCollisionQuad(out, NativeMarshal.ray(ray), NativeMarshal.vector3(p1), NativeMarshal.vector3(p2), NativeMarshal.vector3(p3), NativeMarshal.vector3(p4))
      NativeMarshal.readRayCollision(out)

  def model(ray: Ray, model: Model): RayCollision =
    model.requireLive()
    Zone:
      val out = alloc[raw.RayCollision]()
      RayscalNative.GetRayCollisionModel(out, NativeMarshal.ray(ray), model.ptr)
      NativeMarshal.readRayCollision(out)

  def hit(result: RayCollision): Boolean = result.hit
  def distance(result: RayCollision): Float = result.distance
  def point(result: RayCollision): Vector3 = result.point
  def normal(result: RayCollision): Vector3 = result.normal
