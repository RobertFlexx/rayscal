package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.*

object MaterialMaps:
  val Albedo = 0
  val Metalness = 1
  val Normal = 2
  val Roughness = 3
  val Occlusion = 4
  val Emission = 5
  val Height = 6
  val Cubemap = 7
  val Irradiance = 8
  val Prefilter = 9
  val Brdf = 10
  val Diffuse = Albedo
  val Specular = Metalness

object Models:
  def load(fileName: String): Model =
    val ptr = Zone(RayscalNative.LoadModel(toCString(fileName)))
    checked(ptr, s"Could not load model: $fileName")

  def cube(width: Float, height: Float, length: Float): Model =
    checked(RayscalNative.GenModelCube(width, height, length), "Could not generate cube model")

  def sphere(radius: Float, rings: Int = 32, slices: Int = 32): Model =
    checked(RayscalNative.GenModelSphere(radius, rings, slices), "Could not generate sphere model")

  def plane(width: Float, length: Float, resolutionX: Int = 1, resolutionZ: Int = 1): Model =
    checked(RayscalNative.GenModelPlane(width, length, resolutionX, resolutionZ), "Could not generate plane model")

  def cylinder(radius: Float, height: Float, slices: Int = 32): Model =
    checked(RayscalNative.GenModelCylinder(radius, height, slices), "Could not generate cylinder model")

  def torus(radius: Float, size: Float, radialSegments: Int = 32, sides: Int = 16): Model =
    checked(RayscalNative.GenModelTorus(radius, size, radialSegments, sides), "Could not generate torus model")

  def knot(radius: Float, size: Float, radialSegments: Int = 64, sides: Int = 16): Model =
    checked(RayscalNative.GenModelKnot(radius, size, radialSegments, sides), "Could not generate knot model")

  def isValid(model: Model): Boolean =
    model.requireLive()
    RayscalNative.IsModelValid(model.ptr)

  def unload(model: Model): Unit =
    if !model.disposed then
      RayscalNative.UnloadModel(model.ptr)
      model.disposed = true

  def withModel(fileName: String)(body: Model => Unit): Unit =
    val model = load(fileName)
    try body(model)
    finally unload(model)

  def withCube(width: Float, height: Float, length: Float)(body: Model => Unit): Unit =
    val model = cube(width, height, length)
    try body(model)
    finally unload(model)

  def draw(model: Model, position: Vector3, scale: Float, tint: Color): Unit =
    Zone:
      model.requireLive()
      RayscalNative.DrawModel(model.ptr, NativeCopies.vector3(position), scale, NativeCopies.color(tint))

  def draw(model: Model, position: Vector3, rotationAxis: Vector3, rotationAngle: Float, scale: Vector3, tint: Color): Unit =
    Zone:
      model.requireLive()
      RayscalNative.DrawModelEx(model.ptr, NativeCopies.vector3(position), NativeCopies.vector3(rotationAxis), rotationAngle, NativeCopies.vector3(scale), NativeCopies.color(tint))

  def drawWires(model: Model, position: Vector3, scale: Float, tint: Color): Unit =
    Zone:
      model.requireLive()
      RayscalNative.DrawModelWires(model.ptr, NativeCopies.vector3(position), scale, NativeCopies.color(tint))

  def boundingBox(model: Model): BoundingBox =
    Zone:
      model.requireLive()
      val out = stackalloc[BoundingBox]()
      RayscalNative.GetModelBoundingBox(out, model.ptr)
      !out

  def meshCount(model: Model): Int =
    model.requireLive()
    RayscalNative.ModelMeshCount(model.ptr)

  def materialCount(model: Model): Int =
    model.requireLive()
    RayscalNative.ModelMaterialCount(model.ptr)

  def setMaterialShader(model: Model, material: Int, shader: Shader): Unit =
    Zone:
      model.requireLive()
      shader.requireLive()
      require(RayscalNative.ModelSetMaterialShader(model.ptr, material, shader.ptr), s"Invalid material index: $material")

  def setMaterialColor(model: Model, material: Int, map: Int, color: Color): Unit =
    Zone:
      model.requireLive()
      require(RayscalNative.ModelSetMaterialColor(model.ptr, material, map, NativeCopies.color(color)), s"Invalid material/map index: $material/$map")

  def setMaterialValue(model: Model, material: Int, map: Int, value: Float): Unit =
    model.requireLive()
    require(RayscalNative.ModelSetMaterialValue(model.ptr, material, map, value), s"Invalid material/map index: $material/$map")

  def setMaterialTextureBorrowed(model: Model, material: Int, map: Int, texture: Texture2D): Unit =
    Zone:
      model.requireLive()
      texture.requireLive()
      require(RayscalNative.ModelSetMaterialTexture(model.ptr, material, map, texture.ptr), s"Invalid material/map index: $material/$map")

  private def checked(ptr: Ptr[Byte], message: String): Model =
    if ptr == null then throw new OutOfMemoryError("Could not allocate Model")
    val model = new Model(ptr)
    if !isValid(model) then
      unload(model)
      throw new IllegalArgumentException(message)
    model
