package rayscal

import rayscal.raw.{Rlgl as RawRlgl}
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

object Rlgl:
  enum MatrixMode(val value: Int):
    case ModelView extends MatrixMode(0x1700)
    case Projection extends MatrixMode(0x1701)
    case Texture extends MatrixMode(0x1702)

  enum DrawMode(val value: Int):
    case Lines extends DrawMode(0x0001)
    case Triangles extends DrawMode(0x0004)
    case Quads extends DrawMode(0x0007)

  enum BlendMode(val value: Int):
    case Alpha extends BlendMode(0)
    case Additive extends BlendMode(1)
    case Multiplied extends BlendMode(2)
    case AddColors extends BlendMode(3)
    case SubtractColors extends BlendMode(4)
    case AlphaPremultiply extends BlendMode(5)
    case Custom extends BlendMode(6)
    case CustomSeparate extends BlendMode(7)

  enum CullFace(val value: Int):
    case Front extends CullFace(0)
    case Back extends CullFace(1)

  def matrixMode(mode: MatrixMode): Unit =
    RawRlgl.rlMatrixMode(mode.value)

  def pushMatrix(body: => Unit): Unit =
    RawRlgl.rlMatrixMode(MatrixMode.ModelView.value)
    RawRlgl.rlPushMatrix()
    try body
    finally
      RawRlgl.rlMatrixMode(MatrixMode.ModelView.value)
      RawRlgl.rlPopMatrix()

  def loadIdentity(): Unit =
    RawRlgl.rlLoadIdentity()

  def translate(x: Float, y: Float, z: Float): Unit =
    RawRlgl.rlTranslatef(x, y, z)

  def rotate(angle: Float, x: Float, y: Float, z: Float): Unit =
    RawRlgl.rlRotatef(angle, x, y, z)

  def scale(x: Float, y: Float, z: Float): Unit =
    RawRlgl.rlScalef(x, y, z)

  def setClipPlanes(nearPlane: Double, farPlane: Double): Unit =
    RawRlgl.rlSetClipPlanes(nearPlane, farPlane)

  def begin(mode: DrawMode)(body: => Unit): Unit =
    RawRlgl.rlBegin(mode.value)
    try body
    finally RawRlgl.rlEnd()

  def vertex2i(x: Int, y: Int): Unit =
    RawRlgl.rlVertex2i(x, y)

  def vertex2f(x: Float, y: Float): Unit =
    RawRlgl.rlVertex2f(x, y)

  def vertex3f(x: Float, y: Float, z: Float): Unit =
    RawRlgl.rlVertex3f(x, y, z)

  def texCoord2f(x: Float, y: Float): Unit =
    RawRlgl.rlTexCoord2f(x, y)

  def normal3f(x: Float, y: Float, z: Float): Unit =
    RawRlgl.rlNormal3f(x, y, z)

  def color4ub(r: Int, g: Int, b: Int, a: Int): Unit =
    RawRlgl.rlColor4ub(r.toUByte, g.toUByte, b.toUByte, a.toUByte)

  def color3f(r: Float, g: Float, b: Float): Unit =
    RawRlgl.rlColor3f(r, g, b)

  def color4f(r: Float, g: Float, b: Float, a: Float): Unit =
    RawRlgl.rlColor4f(r, g, b, a)

  def enableTexture(id: Int): Unit =
    RawRlgl.rlEnableTexture(id.toUInt)

  def disableTexture(): Unit =
    RawRlgl.rlDisableTexture()

  def enableTextureCubemap(id: Int): Unit =
    RawRlgl.rlEnableTextureCubemap(id.toUInt)

  def disableTextureCubemap(): Unit =
    RawRlgl.rlDisableTextureCubemap()

  def setTexture(id: Int): Unit =
    RawRlgl.rlSetTexture(id.toUInt)

  def enableShader(id: Int): Unit =
    RawRlgl.rlEnableShader(id.toUInt)

  def disableShader(): Unit =
    RawRlgl.rlDisableShader()

  def enableFramebuffer(id: Int): Unit =
    RawRlgl.rlEnableFramebuffer(id.toUInt)

  def disableFramebuffer(): Unit =
    RawRlgl.rlDisableFramebuffer()

  def enableColorBlend(): Unit =
    RawRlgl.rlEnableColorBlend()

  def disableColorBlend(): Unit =
    RawRlgl.rlDisableColorBlend()

  def enableDepthTest(): Unit =
    RawRlgl.rlEnableDepthTest()

  def disableDepthTest(): Unit =
    RawRlgl.rlDisableDepthTest()

  def enableDepthMask(): Unit =
    RawRlgl.rlEnableDepthMask()

  def disableDepthMask(): Unit =
    RawRlgl.rlDisableDepthMask()

  def enableBackfaceCulling(): Unit =
    RawRlgl.rlEnableBackfaceCulling()

  def disableBackfaceCulling(): Unit =
    RawRlgl.rlDisableBackfaceCulling()

  def colorMask(r: Boolean, g: Boolean, b: Boolean, a: Boolean): Unit =
    RawRlgl.rlColorMask(r, g, b, a)

  def setCullFace(mode: CullFace): Unit =
    RawRlgl.rlSetCullFace(mode.value)

  def enableScissorTest(): Unit =
    RawRlgl.rlEnableScissorTest()

  def disableScissorTest(): Unit =
    RawRlgl.rlDisableScissorTest()

  def enableWireMode(): Unit =
    RawRlgl.rlEnableWireMode()

  def enablePointMode(): Unit =
    RawRlgl.rlEnablePointMode()

  def disableWireMode(): Unit =
    RawRlgl.rlDisableWireMode()

  def setLineWidth(width: Float): Unit =
    RawRlgl.rlSetLineWidth(width)

  def enableSmoothLines(): Unit =
    RawRlgl.rlEnableSmoothLines()

  def disableSmoothLines(): Unit =
    RawRlgl.rlDisableSmoothLines()

  def enableStereoRender(): Unit =
    RawRlgl.rlEnableStereoRender()

  def disableStereoRender(): Unit =
    RawRlgl.rlDisableStereoRender()

  def setBlendMode(mode: BlendMode): Unit =
    RawRlgl.rlSetBlendMode(mode.value)

  def setBlendFactors(glSrcFactor: Int, glDstFactor: Int, glEquation: Int): Unit =
    RawRlgl.rlSetBlendFactors(glSrcFactor, glDstFactor, glEquation)

  def setBlendFactorsSeparate(
      glSrcRGB: Int,
      glDstRGB: Int,
      glSrcAlpha: Int,
      glDstAlpha: Int,
      glEqRGB: Int,
      glEqAlpha: Int
  ): Unit =
    RawRlgl.rlSetBlendFactorsSeparate(glSrcRGB, glDstRGB, glSrcAlpha, glDstAlpha, glEqRGB, glEqAlpha)

  def setFramebufferWidth(width: Int): Unit =
    RawRlgl.rlSetFramebufferWidth(width)

  def setFramebufferHeight(height: Int): Unit =
    RawRlgl.rlSetFramebufferHeight(height)

  def loadDrawCube(): Unit =
    RawRlgl.rlLoadDrawCube()

  def loadDrawQuad(): Unit =
    RawRlgl.rlLoadDrawQuad()

  def version: Int =
    RawRlgl.rlGetVersion()

  def framebufferWidth: Int =
    RawRlgl.rlGetFramebufferWidth()

  def framebufferHeight: Int =
    RawRlgl.rlGetFramebufferHeight()

  def isStereoRenderEnabled: Boolean =
    RawRlgl.rlIsStereoRenderEnabled()

  def loadVertexArray(): Int =
    RawRlgl.rlLoadVertexArray().toInt

  def unloadVertexArray(id: Int): Unit =
    RawRlgl.rlUnloadVertexArray(id.toUInt)

  def unloadVertexBuffer(id: Int): Unit =
    RawRlgl.rlUnloadVertexBuffer(id.toUInt)

  def drawVertexArray(offset: Int, count: Int): Unit =
    RawRlgl.rlDrawVertexArray(offset, count)

  def drawVertexArrayInstanced(offset: Int, count: Int, instances: Int): Unit =
    RawRlgl.rlDrawVertexArrayInstanced(offset, count, instances)

  def loadFramebuffer(): Int =
    RawRlgl.rlLoadFramebuffer().toInt

  def framebufferComplete(id: Int): Boolean =
    RawRlgl.rlFramebufferComplete(id.toUInt)

  def unloadFramebuffer(id: Int): Unit =
    RawRlgl.rlUnloadFramebuffer(id.toUInt)

  def loadShaderCode(vertexShaderCode: String, fragmentShaderCode: String): Int =
    Zone:
      RawRlgl.rlLoadShaderProgram(toCString(vertexShaderCode), toCString(fragmentShaderCode)).toInt

  def compileShader(shaderCode: String, shaderType: Int): Int =
    Zone:
      RawRlgl.rlLoadShader(toCString(shaderCode), shaderType).toInt

  def loadShaderProgram(vertexShaderId: Int, fragmentShaderId: Int): Int =
    RawRlgl.rlLoadShaderProgramEx(vertexShaderId.toUInt, fragmentShaderId.toUInt).toInt

  def unloadShader(id: Int): Unit =
    RawRlgl.rlUnloadShader(id.toUInt)

  def unloadShaderProgram(id: Int): Unit =
    RawRlgl.rlUnloadShaderProgram(id.toUInt)

  def uniformLocation(shaderId: Int, uniformName: String): Int =
    Zone:
      RawRlgl.rlGetLocationUniform(shaderId.toUInt, toCString(uniformName))

  def attributeLocation(shaderId: Int, attributeName: String): Int =
    Zone:
      RawRlgl.rlGetLocationAttrib(shaderId.toUInt, toCString(attributeName))

  def setUniformSampler(location: Int, textureId: Int): Unit =
    RawRlgl.rlSetUniformSampler(location, textureId.toUInt)

  def loadComputeShaderProgram(shaderId: Int): Int =
    RawRlgl.rlLoadShaderProgramCompute(shaderId.toUInt).toInt

  def computeShaderDispatch(groupX: Int, groupY: Int, groupZ: Int): Unit =
    RawRlgl.rlComputeShaderDispatch(groupX.toUInt, groupY.toUInt, groupZ.toUInt)

  def unloadShaderBuffer(id: Int): Unit =
    RawRlgl.rlUnloadShaderBuffer(id.toUInt)

  def bindShaderBuffer(id: Int, index: Int): Unit =
    RawRlgl.rlBindShaderBuffer(id.toUInt, index.toUInt)
