package rayscal.raw

import scala.scalanative.unsafe.*

@extern
@link("raylib")
object Rlgl:
  def rlMatrixMode(mode: CInt): Unit = extern
  def rlPushMatrix(): Unit = extern
  def rlPopMatrix(): Unit = extern
  def rlLoadIdentity(): Unit = extern
  def rlTranslatef(x: CFloat, y: CFloat, z: CFloat): Unit = extern
  def rlRotatef(angle: CFloat, x: CFloat, y: CFloat, z: CFloat): Unit = extern
  def rlScalef(x: CFloat, y: CFloat, z: CFloat): Unit = extern
  def rlSetClipPlanes(nearPlane: CDouble, farPlane: CDouble): Unit = extern

  def rlBegin(mode: CInt): Unit = extern
  def rlEnd(): Unit = extern
  def rlVertex2i(x: CInt, y: CInt): Unit = extern
  def rlVertex2f(x: CFloat, y: CFloat): Unit = extern
  def rlVertex3f(x: CFloat, y: CFloat, z: CFloat): Unit = extern
  def rlTexCoord2f(x: CFloat, y: CFloat): Unit = extern
  def rlNormal3f(x: CFloat, y: CFloat, z: CFloat): Unit = extern
  def rlColor4ub(r: CUnsignedChar, g: CUnsignedChar, b: CUnsignedChar, a: CUnsignedChar): Unit = extern
  def rlColor3f(r: CFloat, g: CFloat, b: CFloat): Unit = extern
  def rlColor4f(r: CFloat, g: CFloat, b: CFloat, a: CFloat): Unit = extern

  def rlEnableTexture(id: CUnsignedInt): Unit = extern
  def rlDisableTexture(): Unit = extern
  def rlEnableTextureCubemap(id: CUnsignedInt): Unit = extern
  def rlDisableTextureCubemap(): Unit = extern
  def rlEnableShader(id: CUnsignedInt): Unit = extern
  def rlDisableShader(): Unit = extern
  def rlEnableFramebuffer(id: CUnsignedInt): Unit = extern
  def rlDisableFramebuffer(): Unit = extern

  def rlEnableColorBlend(): Unit = extern
  def rlDisableColorBlend(): Unit = extern
  def rlEnableDepthTest(): Unit = extern
  def rlDisableDepthTest(): Unit = extern
  def rlEnableDepthMask(): Unit = extern
  def rlDisableDepthMask(): Unit = extern
  def rlEnableBackfaceCulling(): Unit = extern
  def rlDisableBackfaceCulling(): Unit = extern
  def rlColorMask(r: CBool, g: CBool, b: CBool, a: CBool): Unit = extern
  def rlSetCullFace(mode: CInt): Unit = extern
  def rlEnableScissorTest(): Unit = extern
  def rlDisableScissorTest(): Unit = extern
  def rlEnableWireMode(): Unit = extern
  def rlEnablePointMode(): Unit = extern
  def rlDisableWireMode(): Unit = extern
  def rlSetLineWidth(width: CFloat): Unit = extern
  def rlEnableSmoothLines(): Unit = extern
  def rlDisableSmoothLines(): Unit = extern
  def rlEnableStereoRender(): Unit = extern
  def rlDisableStereoRender(): Unit = extern

  def rlSetBlendMode(mode: CInt): Unit = extern
  def rlSetBlendFactors(glSrcFactor: CInt, glDstFactor: CInt, glEquation: CInt): Unit = extern
  def rlSetBlendFactorsSeparate(
      glSrcRGB: CInt,
      glDstRGB: CInt,
      glSrcAlpha: CInt,
      glDstAlpha: CInt,
      glEqRGB: CInt,
      glEqAlpha: CInt
  ): Unit = extern

  def rlSetFramebufferWidth(width: CInt): Unit = extern
  def rlSetFramebufferHeight(height: CInt): Unit = extern
  def rlSetTexture(id: CUnsignedInt): Unit = extern

  def rlLoadDrawCube(): Unit = extern
  def rlLoadDrawQuad(): Unit = extern

  def rlGetVersion(): CInt = extern
  def rlGetFramebufferWidth(): CInt = extern
  def rlGetFramebufferHeight(): CInt = extern
  def rlIsStereoRenderEnabled(): CBool = extern

  def rlLoadVertexArray(): CUnsignedInt = extern
  def rlLoadVertexBuffer(buffer: Ptr[Byte], size: CInt, dynamic: CBool): CUnsignedInt = extern
  def rlLoadVertexBufferElement(buffer: Ptr[Byte], size: CInt, dynamic: CBool): CUnsignedInt = extern
  def rlUpdateVertexBuffer(bufferId: CUnsignedInt, data: Ptr[Byte], dataSize: CInt, offset: CInt): Unit = extern
  def rlUpdateVertexBufferElements(id: CUnsignedInt, data: Ptr[Byte], dataSize: CInt, offset: CInt): Unit = extern
  def rlUnloadVertexArray(vaoId: CUnsignedInt): Unit = extern
  def rlUnloadVertexBuffer(vboId: CUnsignedInt): Unit = extern
  def rlSetVertexAttribute(index: CUnsignedInt, compSize: CInt, `type`: CInt, normalized: CBool, stride: CInt, offset: CInt): Unit = extern
  def rlSetVertexAttributeDivisor(index: CUnsignedInt, divisor: CInt): Unit = extern
  def rlSetVertexAttributeDefault(locIndex: CInt, value: Ptr[Byte], attribType: CInt, count: CInt): Unit = extern
  def rlDrawVertexArray(offset: CInt, count: CInt): Unit = extern
  def rlDrawVertexArrayElements(offset: CInt, count: CInt, buffer: Ptr[Byte]): Unit = extern
  def rlDrawVertexArrayInstanced(offset: CInt, count: CInt, instances: CInt): Unit = extern
  def rlDrawVertexArrayElementsInstanced(offset: CInt, count: CInt, buffer: Ptr[Byte], instances: CInt): Unit = extern

  def rlLoadTexture(data: Ptr[Byte], width: CInt, height: CInt, format: CInt, mipmapCount: CInt): CUnsignedInt = extern
  def rlLoadTextureDepth(width: CInt, height: CInt, useRenderBuffer: CBool): CUnsignedInt = extern
  def rlLoadTextureCubemap(data: Ptr[Byte], size: CInt, format: CInt, mipmapCount: CInt): CUnsignedInt = extern
  def rlUpdateTexture(id: CUnsignedInt, offsetX: CInt, offsetY: CInt, width: CInt, height: CInt, format: CInt, data: Ptr[Byte]): Unit = extern
  def rlUnloadTexture(id: CUnsignedInt): Unit = extern
  def rlReadTexturePixels(id: CUnsignedInt, width: CInt, height: CInt, format: CInt): Ptr[Byte] = extern
  def rlReadScreenPixels(width: CInt, height: CInt): Ptr[CUnsignedChar] = extern

  def rlLoadFramebuffer(): CUnsignedInt = extern
  def rlFramebufferAttach(fboId: CUnsignedInt, texId: CUnsignedInt, attachType: CInt, texType: CInt, mipLevel: CInt): Unit = extern
  def rlFramebufferComplete(id: CUnsignedInt): CBool = extern
  def rlUnloadFramebuffer(id: CUnsignedInt): Unit = extern

  def rlLoadShaderCode(vsCode: CString, fsCode: CString): CUnsignedInt = extern
  def rlCompileShader(shaderCode: CString, `type`: CInt): CUnsignedInt = extern
  def rlLoadShaderProgram(vShaderId: CUnsignedInt, fShaderId: CUnsignedInt): CUnsignedInt = extern
  def rlUnloadShaderProgram(id: CUnsignedInt): Unit = extern
  def rlGetLocationUniform(shaderId: CUnsignedInt, uniformName: CString): CInt = extern
  def rlGetLocationAttrib(shaderId: CUnsignedInt, attribName: CString): CInt = extern
  def rlSetUniform(locIndex: CInt, value: Ptr[Byte], uniformType: CInt, count: CInt): Unit = extern
  def rlSetUniformMatrix(locIndex: CInt, mat: Matrix): Unit = extern
  def rlSetUniformMatrices(locIndex: CInt, mat: Ptr[Matrix], count: CInt): Unit = extern
  def rlSetUniformSampler(locIndex: CInt, textureId: CUnsignedInt): Unit = extern
  def rlSetShader(id: CUnsignedInt, locs: Ptr[CInt]): Unit = extern

  def rlLoadComputeShaderProgram(shaderId: CUnsignedInt): CUnsignedInt = extern
  def rlComputeShaderDispatch(groupX: CUnsignedInt, groupY: CUnsignedInt, groupZ: CUnsignedInt): Unit = extern

  def rlLoadShaderBuffer(size: CUnsignedInt, data: Ptr[Byte], usageHint: CInt): CUnsignedInt = extern
  def rlUnloadShaderBuffer(ssboId: CUnsignedInt): Unit = extern
  def rlUpdateShaderBuffer(id: CUnsignedInt, data: Ptr[Byte], dataSize: CUnsignedInt, offset: CUnsignedInt): Unit = extern
  def rlBindShaderBuffer(id: CUnsignedInt, index: CUnsignedInt): Unit = extern
  def rlReadShaderBuffer(id: CUnsignedInt, dest: Ptr[Byte], count: CUnsignedInt, offset: CUnsignedInt): Unit = extern
  def rlCopyShaderBuffer(destId: CUnsignedInt, srcId: CUnsignedInt, destOffset: CUnsignedInt, srcOffset: CUnsignedInt, count: CUnsignedInt): Unit = extern
