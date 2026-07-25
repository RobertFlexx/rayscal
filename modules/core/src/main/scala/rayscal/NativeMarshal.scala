package rayscal

import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

/** Internal marshaling between Scala-owned values and temporary raw C structs.
  *
  * Input helpers allocate native memory only for the duration of `body`.
  * Output helpers copy every field into a Scala-owned value before leaving the
  * Zone. Nothing returned from these helpers is backed by temporary native memory.
  */
private[rayscal] object NativeMarshal:

  private def clampByte(value: Int): UByte =
    math.max(0, math.min(255, value)).toUByte

  def withColor[A](value: Color)(body: Ptr[raw.Color] => A): A =
    Zone:
      val native = alloc[raw.Color]()
      (!native)._1 = clampByte(value.r)
      (!native)._2 = clampByte(value.g)
      (!native)._3 = clampByte(value.b)
      (!native)._4 = clampByte(value.a)
      body(native)

  def withVector2[A](value: Vector2)(body: Ptr[raw.Vector2] => A): A =
    Zone:
      val native = alloc[raw.Vector2]()
      (!native)._1 = value.x
      (!native)._2 = value.y
      body(native)

  def withVector3[A](value: Vector3)(body: Ptr[raw.Vector3] => A): A =
    Zone:
      val native = alloc[raw.Vector3]()
      (!native)._1 = value.x
      (!native)._2 = value.y
      (!native)._3 = value.z
      body(native)

  def withVector4[A](value: Vector4)(body: Ptr[raw.Vector4] => A): A =
    Zone:
      val native = alloc[raw.Vector4]()
      (!native)._1 = value.x
      (!native)._2 = value.y
      (!native)._3 = value.z
      (!native)._4 = value.w
      body(native)

  def withMatrix[A](value: Matrix)(body: Ptr[raw.Matrix] => A): A =
    Zone:
      val native = alloc[raw.Matrix]()
      writeMatrix(native, value)
      body(native)

  def withRectangle[A](value: Rectangle)(body: Ptr[raw.Rectangle] => A): A =
    Zone:
      val native = alloc[raw.Rectangle]()
      (!native)._1 = value.x
      (!native)._2 = value.y
      (!native)._3 = value.width
      (!native)._4 = value.height
      body(native)

  def withBoundingBox[A](value: BoundingBox)(body: Ptr[raw.BoundingBox] => A): A =
    Zone:
      val native = alloc[raw.BoundingBox]()
      writeBoundingBox(native, value)
      body(native)

  def withRay[A](value: Ray)(body: Ptr[raw.Ray] => A): A =
    Zone:
      val native = alloc[raw.Ray]()
      writeRay(native, value)
      body(native)

  def withCamera2D[A](value: Camera2D)(body: Ptr[raw.Camera2D] => A): A =
    Zone:
      val native = alloc[raw.Camera2D]()
      writeCamera2D(native, value)
      body(native)

  def withCamera3D[A](value: Camera3D)(body: Ptr[raw.Camera3D] => A): A =
    Zone:
      val native = alloc[raw.Camera3D]()
      writeCamera3D(native, value)
      body(native)

  /** Allocate several inputs in one Zone for multi-struct calls. */
  def withZone[A](body: Zone ?=> A): A =
    Zone(body)

  def writeColor(native: Ptr[raw.Color], value: Color): Unit =
    (!native)._1 = clampByte(value.r)
    (!native)._2 = clampByte(value.g)
    (!native)._3 = clampByte(value.b)
    (!native)._4 = clampByte(value.a)

  def writeVector2(native: Ptr[raw.Vector2], value: Vector2): Unit =
    (!native)._1 = value.x
    (!native)._2 = value.y

  def writeVector3(native: Ptr[raw.Vector3], value: Vector3): Unit =
    (!native)._1 = value.x
    (!native)._2 = value.y
    (!native)._3 = value.z

  def writeRectangle(native: Ptr[raw.Rectangle], value: Rectangle): Unit =
    (!native)._1 = value.x
    (!native)._2 = value.y
    (!native)._3 = value.width
    (!native)._4 = value.height

  def writeMatrix(native: Ptr[raw.Matrix], value: Matrix): Unit =
    (!native)._1 = value.m0
    (!native)._2 = value.m4
    (!native)._3 = value.m8
    (!native)._4 = value.m12
    (!native)._5 = value.m1
    (!native)._6 = value.m5
    (!native)._7 = value.m9
    (!native)._8 = value.m13
    (!native)._9 = value.m2
    (!native)._10 = value.m6
    (!native)._11 = value.m10
    (!native)._12 = value.m14
    (!native)._13 = value.m3
    (!native)._14 = value.m7
    (!native)._15 = value.m11
    (!native)._16 = value.m15

  def writeBoundingBox(native: Ptr[raw.BoundingBox], value: BoundingBox): Unit =
    (!native)._1 = value.min.x
    (!native)._2 = value.min.y
    (!native)._3 = value.min.z
    (!native)._4 = value.max.x
    (!native)._5 = value.max.y
    (!native)._6 = value.max.z

  def writeRay(native: Ptr[raw.Ray], value: Ray): Unit =
    (!native)._1 = value.position.x
    (!native)._2 = value.position.y
    (!native)._3 = value.position.z
    (!native)._4 = value.direction.x
    (!native)._5 = value.direction.y
    (!native)._6 = value.direction.z

  def writeCamera2D(native: Ptr[raw.Camera2D], value: Camera2D): Unit =
    (!native)._1 = value.offset.x
    (!native)._2 = value.offset.y
    (!native)._3 = value.target.x
    (!native)._4 = value.target.y
    (!native)._5 = value.rotation
    (!native)._6 = value.zoom

  def writeCamera3D(native: Ptr[raw.Camera3D], value: Camera3D): Unit =
    (!native)._1 = value.position.x
    (!native)._2 = value.position.y
    (!native)._3 = value.position.z
    (!native)._4 = value.target.x
    (!native)._5 = value.target.y
    (!native)._6 = value.target.z
    (!native)._7 = value.up.x
    (!native)._8 = value.up.y
    (!native)._9 = value.up.z
    (!native)._10 = value.fovy
    (!native)._11 = value.projection

  def readColor(native: Ptr[raw.Color]): Color =
    Color((!native)._1.toInt, (!native)._2.toInt, (!native)._3.toInt, (!native)._4.toInt)

  def readVector2(native: Ptr[raw.Vector2]): Vector2 =
    Vector2((!native)._1, (!native)._2)

  def readVector3(native: Ptr[raw.Vector3]): Vector3 =
    Vector3((!native)._1, (!native)._2, (!native)._3)

  def readVector4(native: Ptr[raw.Vector4]): Vector4 =
    Vector4((!native)._1, (!native)._2, (!native)._3, (!native)._4)

  def readMatrix(native: Ptr[raw.Matrix]): Matrix =
    Matrix(
      (!native)._1,
      (!native)._2,
      (!native)._3,
      (!native)._4,
      (!native)._5,
      (!native)._6,
      (!native)._7,
      (!native)._8,
      (!native)._9,
      (!native)._10,
      (!native)._11,
      (!native)._12,
      (!native)._13,
      (!native)._14,
      (!native)._15,
      (!native)._16
    )

  def readRectangle(native: Ptr[raw.Rectangle]): Rectangle =
    Rectangle((!native)._1, (!native)._2, (!native)._3, (!native)._4)

  def readBoundingBox(native: Ptr[raw.BoundingBox]): BoundingBox =
    BoundingBox(Vector3((!native)._1, (!native)._2, (!native)._3), Vector3((!native)._4, (!native)._5, (!native)._6))

  def readRay(native: Ptr[raw.Ray]): Ray =
    Ray(Vector3((!native)._1, (!native)._2, (!native)._3), Vector3((!native)._4, (!native)._5, (!native)._6))

  def readCamera2D(native: Ptr[raw.Camera2D]): Camera2D =
    Camera2D(Vector2((!native)._1, (!native)._2), Vector2((!native)._3, (!native)._4), (!native)._5, (!native)._6)

  def readCamera3D(native: Ptr[raw.Camera3D]): Camera3D =
    Camera3D(
      Vector3((!native)._1, (!native)._2, (!native)._3),
      Vector3((!native)._4, (!native)._5, (!native)._6),
      Vector3((!native)._7, (!native)._8, (!native)._9),
      (!native)._10,
      (!native)._11
    )

  def readRayCollision(native: Ptr[raw.RayCollision]): RayCollision =
    RayCollision(
      (!native)._1,
      (!native)._2,
      Vector3((!native)._3, (!native)._4, (!native)._5),
      Vector3((!native)._6, (!native)._7, (!native)._8)
    )

  def readColor(call: Ptr[raw.Color] => Unit): Color =
    Zone:
      val out = alloc[raw.Color]()
      call(out)
      readColor(out)

  def readVector2(call: Ptr[raw.Vector2] => Unit): Vector2 =
    Zone:
      val out = alloc[raw.Vector2]()
      call(out)
      readVector2(out)

  def readVector3(call: Ptr[raw.Vector3] => Unit): Vector3 =
    Zone:
      val out = alloc[raw.Vector3]()
      call(out)
      readVector3(out)

  def readVector4(call: Ptr[raw.Vector4] => Unit): Vector4 =
    Zone:
      val out = alloc[raw.Vector4]()
      call(out)
      readVector4(out)

  def readMatrix(call: Ptr[raw.Matrix] => Unit): Matrix =
    Zone:
      val out = alloc[raw.Matrix]()
      call(out)
      readMatrix(out)

  def readRectangle(call: Ptr[raw.Rectangle] => Unit): Rectangle =
    Zone:
      val out = alloc[raw.Rectangle]()
      call(out)
      readRectangle(out)

  def readBoundingBox(call: Ptr[raw.BoundingBox] => Unit): BoundingBox =
    Zone:
      val out = alloc[raw.BoundingBox]()
      call(out)
      readBoundingBox(out)

  def readRay(call: Ptr[raw.Ray] => Unit): Ray =
    Zone:
      val out = alloc[raw.Ray]()
      call(out)
      readRay(out)

  def readCamera2D(call: Ptr[raw.Camera2D] => Unit): Camera2D =
    Zone:
      val out = alloc[raw.Camera2D]()
      call(out)
      readCamera2D(out)

  def readCamera3D(call: Ptr[raw.Camera3D] => Unit): Camera3D =
    Zone:
      val out = alloc[raw.Camera3D]()
      call(out)
      readCamera3D(out)

  def readRayCollision(call: Ptr[raw.RayCollision] => Unit): RayCollision =
    Zone:
      val out = alloc[raw.RayCollision]()
      call(out)
      readRayCollision(out)

  /** Multi-argument helpers used by drawing and collision wrappers. */
  def color(value: Color)(using Zone): Ptr[raw.Color] =
    val native = alloc[raw.Color]()
    writeColor(native, value)
    native

  def vector2(value: Vector2)(using Zone): Ptr[raw.Vector2] =
    val native = alloc[raw.Vector2]()
    writeVector2(native, value)
    native

  def vector3(value: Vector3)(using Zone): Ptr[raw.Vector3] =
    val native = alloc[raw.Vector3]()
    writeVector3(native, value)
    native

  def vector4(value: Vector4)(using Zone): Ptr[raw.Vector4] =
    val native = alloc[raw.Vector4]()
    (!native)._1 = value.x
    (!native)._2 = value.y
    (!native)._3 = value.z
    (!native)._4 = value.w
    native

  def matrix(value: Matrix)(using Zone): Ptr[raw.Matrix] =
    val native = alloc[raw.Matrix]()
    writeMatrix(native, value)
    native

  def rectangle(value: Rectangle)(using Zone): Ptr[raw.Rectangle] =
    val native = alloc[raw.Rectangle]()
    writeRectangle(native, value)
    native

  def boundingBox(value: BoundingBox)(using Zone): Ptr[raw.BoundingBox] =
    val native = alloc[raw.BoundingBox]()
    writeBoundingBox(native, value)
    native

  def ray(value: Ray)(using Zone): Ptr[raw.Ray] =
    val native = alloc[raw.Ray]()
    writeRay(native, value)
    native

  def camera2D(value: Camera2D)(using Zone): Ptr[raw.Camera2D] =
    val native = alloc[raw.Camera2D]()
    writeCamera2D(native, value)
    native

  def camera3D(value: Camera3D)(using Zone): Ptr[raw.Camera3D] =
    val native = alloc[raw.Camera3D]()
    writeCamera3D(native, value)
    native
