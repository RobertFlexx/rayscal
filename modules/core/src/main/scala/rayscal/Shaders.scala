package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.libc.stdlib
import scala.scalanative.unsafe.*

object Shaders:
  private object UniformType:
    val Float = 0
    val Vec2 = 1
    val Vec3 = 2
    val Vec4 = 3
    val Int = 4
    val IVec2 = 5
    val IVec3 = 6
    val IVec4 = 7

  def load(vertexShaderFile: String, fragmentShaderFile: String): Shader =
    Zone:
      val shader = allocate()
      RayscalNative.LoadShader(shader.ptr, toCString(vertexShaderFile), toCString(fragmentShaderFile))
      shader

  def fromMemory(vertexShaderCode: String, fragmentShaderCode: String): Shader =
    Zone:
      val shader = allocate()
      RayscalNative.LoadShaderFromMemory(shader.ptr, toCString(vertexShaderCode), toCString(fragmentShaderCode))
      shader

  def isValid(shader: Shader): Boolean =
    shader.requireLive()
    RayscalNative.IsShaderValid(shader.ptr)

  def unload(shader: Shader): Unit =
    if !shader.disposed then
      if RayscalNative.IsShaderValid(shader.ptr) then RayscalNative.UnloadShader(shader.ptr)
      stdlib.free(shader.ptr.asInstanceOf[Ptr[Byte]])
      shader.disposed = true

  def withShader(vertexShaderFile: String, fragmentShaderFile: String)(body: Shader => Unit): Unit =
    val shader = load(vertexShaderFile, fragmentShaderFile)
    try body(shader)
    finally unload(shader)

  def withShaderFromMemory(vertexShaderCode: String, fragmentShaderCode: String)(body: Shader => Unit): Unit =
    val shader = fromMemory(vertexShaderCode, fragmentShaderCode)
    try body(shader)
    finally unload(shader)

  def location(shader: Shader, uniformName: String): Int =
    Zone:
      shader.requireLive()
      RayscalNative.GetShaderLocation(shader.ptr, toCString(uniformName))

  def attributeLocation(shader: Shader, attributeName: String): Int =
    Zone:
      shader.requireLive()
      RayscalNative.GetShaderLocationAttrib(shader.ptr, toCString(attributeName))

  def setTexture(shader: Shader, location: Int, texture: Texture2D): Unit =
    shader.requireLive()
    texture.requireLive()
    RayscalNative.SetShaderValueTexture(shader.ptr, location, texture.ptr)

  def setTexture(shader: Shader, location: Int, texture: TextureView): Unit =
    Zone:
      shader.requireLive()
      RayscalNative.SetShaderValueTexture(shader.ptr, location, texture.native)

  def setFloat(shader: Shader, location: Int, value: Float): Unit =
    Zone:
      val data = stackalloc[CFloat]()
      !data = value
      setValue(shader, location, data, UniformType.Float)

  def setVector2(shader: Shader, location: Int, value: Vector2): Unit =
    Zone:
      val data = stackalloc[CFloat](2)
      data(0) = value._1
      data(1) = value._2
      setValue(shader, location, data, UniformType.Vec2)

  def setVector3(shader: Shader, location: Int, value: Vector3): Unit =
    Zone:
      val data = stackalloc[CFloat](3)
      data(0) = value._1
      data(1) = value._2
      data(2) = value._3
      setValue(shader, location, data, UniformType.Vec3)

  def setVector4(shader: Shader, location: Int, value: Vector4): Unit =
    Zone:
      val data = stackalloc[CFloat](4)
      data(0) = value._1
      data(1) = value._2
      data(2) = value._3
      data(3) = value._4
      setValue(shader, location, data, UniformType.Vec4)

  def setInt(shader: Shader, location: Int, value: Int): Unit =
    Zone:
      val data = stackalloc[CInt]()
      !data = value
      setValue(shader, location, data, UniformType.Int)

  def setMatrix(shader: Shader, location: Int, matrix: Matrix): Unit =
    Zone:
      shader.requireLive()
      RayscalNative.SetShaderValueMatrix(shader.ptr, location, NativeCopies.matrix(matrix))

  def setInts(shader: Shader, location: Int, x: Int, y: Int): Unit =
    setIntVector(shader, location, UniformType.IVec2, x, y)

  def setInts(shader: Shader, location: Int, x: Int, y: Int, z: Int): Unit =
    setIntVector(shader, location, UniformType.IVec3, x, y, z)

  def setInts(shader: Shader, location: Int, x: Int, y: Int, z: Int, w: Int): Unit =
    setIntVector(shader, location, UniformType.IVec4, x, y, z, w)

  private def setIntVector(shader: Shader, location: Int, uniformType: Int, values: Int*): Unit =
    Zone:
      val data = stackalloc[CInt](values.size)
      values.zipWithIndex.foreach((value, index) => data(index) = value)
      setValue(shader, location, data, uniformType)

  private def setValue[T](shader: Shader, location: Int, value: Ptr[T], uniformType: Int)(using zone: Zone): Unit =
    val _ = zone
    shader.requireLive()
    RayscalNative.SetShaderValue(shader.ptr, location, value.asInstanceOf[Ptr[Byte]], uniformType)

  def id(shader: Shader): Int =
    shader.requireLive()
    (!shader.ptr)._1.toInt

  private def allocate(): Shader =
    val ptr = stdlib.malloc(sizeof[raw.Shader]).asInstanceOf[Ptr[raw.Shader]]
    if ptr == null then throw new OutOfMemoryError("Could not allocate Shader")
    new Shader(ptr)
