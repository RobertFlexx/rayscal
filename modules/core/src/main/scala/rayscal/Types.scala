package rayscal

/** Scala-owned plain value types. These are ordinary immutable Scala values,
  * not views into native stack/Zone memory. They are safe to store, copy, and
  * return from methods. Native marshaling happens only for the duration of
  * each FFI call via [[NativeMarshal]].
  */
final case class Color(r: Int, g: Int, b: Int, a: Int):
  @deprecated("use Color.r", since = "0.1.0")
  def _1: Int = r
  @deprecated("use Color.g", since = "0.1.0")
  def _2: Int = g
  @deprecated("use Color.b", since = "0.1.0")
  def _3: Int = b
  @deprecated("use Color.a", since = "0.1.0")
  def _4: Int = a

final case class Vector2(x: Float, y: Float):
  @deprecated("use Vector2.x", since = "0.1.0")
  def _1: Float = x
  @deprecated("use Vector2.y", since = "0.1.0")
  def _2: Float = y

final case class Vector3(x: Float, y: Float, z: Float):
  @deprecated("use Vector3.x", since = "0.1.0")
  def _1: Float = x
  @deprecated("use Vector3.y", since = "0.1.0")
  def _2: Float = y
  @deprecated("use Vector3.z", since = "0.1.0")
  def _3: Float = z

final case class Vector4(x: Float, y: Float, z: Float, w: Float):
  @deprecated("use Vector4.x", since = "0.1.0")
  def _1: Float = x
  @deprecated("use Vector4.y", since = "0.1.0")
  def _2: Float = y
  @deprecated("use Vector4.z", since = "0.1.0")
  def _3: Float = z
  @deprecated("use Vector4.w", since = "0.1.0")
  def _4: Float = w

type Quaternion = Vector4

/** Column-major 4x4 matrix matching raylib's Matrix field layout. */
final case class Matrix(
  m0: Float,
  m4: Float,
  m8: Float,
  m12: Float,
  m1: Float,
  m5: Float,
  m9: Float,
  m13: Float,
  m2: Float,
  m6: Float,
  m10: Float,
  m14: Float,
  m3: Float,
  m7: Float,
  m11: Float,
  m15: Float
)

final case class Rectangle(x: Float, y: Float, width: Float, height: Float):
  @deprecated("use Rectangle.x", since = "0.1.0")
  def _1: Float = x
  @deprecated("use Rectangle.y", since = "0.1.0")
  def _2: Float = y
  @deprecated("use Rectangle.width", since = "0.1.0")
  def _3: Float = width
  @deprecated("use Rectangle.height", since = "0.1.0")
  def _4: Float = height

final case class Camera2D(offset: Vector2, target: Vector2, rotation: Float, zoom: Float):
  @deprecated("use Camera2D.offset.x", since = "0.1.0")
  def _1: Float = offset.x
  @deprecated("use Camera2D.offset.y", since = "0.1.0")
  def _2: Float = offset.y
  @deprecated("use Camera2D.target.x", since = "0.1.0")
  def _3: Float = target.x
  @deprecated("use Camera2D.target.y", since = "0.1.0")
  def _4: Float = target.y
  @deprecated("use Camera2D.rotation", since = "0.1.0")
  def _5: Float = rotation
  @deprecated("use Camera2D.zoom", since = "0.1.0")
  def _6: Float = zoom

final case class Camera3D(
  position: Vector3,
  target: Vector3,
  up: Vector3,
  fovy: Float,
  projection: Int
):
  @deprecated("use Camera3D.position.x", since = "0.1.0")
  def _1: Float = position.x
  @deprecated("use Camera3D.position.y", since = "0.1.0")
  def _2: Float = position.y
  @deprecated("use Camera3D.position.z", since = "0.1.0")
  def _3: Float = position.z
  @deprecated("use Camera3D.target.x", since = "0.1.0")
  def _4: Float = target.x
  @deprecated("use Camera3D.target.y", since = "0.1.0")
  def _5: Float = target.y
  @deprecated("use Camera3D.target.z", since = "0.1.0")
  def _6: Float = target.z
  @deprecated("use Camera3D.up.x", since = "0.1.0")
  def _7: Float = up.x
  @deprecated("use Camera3D.up.y", since = "0.1.0")
  def _8: Float = up.y
  @deprecated("use Camera3D.up.z", since = "0.1.0")
  def _9: Float = up.z
  @deprecated("use Camera3D.fovy", since = "0.1.0")
  def _10: Float = fovy
  @deprecated("use Camera3D.projection", since = "0.1.0")
  def _11: Int = projection

final case class BoundingBox(min: Vector3, max: Vector3):
  @deprecated("use BoundingBox.min.x", since = "0.1.0")
  def _1: Float = min.x
  @deprecated("use BoundingBox.min.y", since = "0.1.0")
  def _2: Float = min.y
  @deprecated("use BoundingBox.min.z", since = "0.1.0")
  def _3: Float = min.z
  @deprecated("use BoundingBox.max.x", since = "0.1.0")
  def _4: Float = max.x
  @deprecated("use BoundingBox.max.y", since = "0.1.0")
  def _5: Float = max.y
  @deprecated("use BoundingBox.max.z", since = "0.1.0")
  def _6: Float = max.z

final case class Ray(position: Vector3, direction: Vector3):
  @deprecated("use Ray.position.x", since = "0.1.0")
  def _1: Float = position.x
  @deprecated("use Ray.position.y", since = "0.1.0")
  def _2: Float = position.y
  @deprecated("use Ray.position.z", since = "0.1.0")
  def _3: Float = position.z
  @deprecated("use Ray.direction.x", since = "0.1.0")
  def _4: Float = direction.x
  @deprecated("use Ray.direction.y", since = "0.1.0")
  def _5: Float = direction.y
  @deprecated("use Ray.direction.z", since = "0.1.0")
  def _6: Float = direction.z

final case class RayCollision(hit: Boolean, distance: Float, point: Vector3, normal: Vector3):
  @deprecated("use RayCollision.hit", since = "0.1.0")
  def _1: Boolean = hit
  @deprecated("use RayCollision.distance", since = "0.1.0")
  def _2: Float = distance
  @deprecated("use RayCollision.point.x", since = "0.1.0")
  def _3: Float = point.x
  @deprecated("use RayCollision.point.y", since = "0.1.0")
  def _4: Float = point.y
  @deprecated("use RayCollision.point.z", since = "0.1.0")
  def _5: Float = point.z
  @deprecated("use RayCollision.normal.x", since = "0.1.0")
  def _6: Float = normal.x
  @deprecated("use RayCollision.normal.y", since = "0.1.0")
  def _7: Float = normal.y
  @deprecated("use RayCollision.normal.z", since = "0.1.0")
  def _8: Float = normal.z
