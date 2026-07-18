package rayscal.raw

import scala.scalanative.unsafe.*

@extern
object RayscalNative:
  @name("rayscal_raylib_version_major")
  def RaylibVersionMajor(): CInt = extern

  @name("rayscal_raylib_version_minor")
  def RaylibVersionMinor(): CInt = extern

  @name("rayscal_raylib_version_patch")
  def RaylibVersionPatch(): CInt = extern

  @name("rayscal_sizeof_Color")
  def SizeOfColor(): CInt = extern

  @name("rayscal_sizeof_Vector2")
  def SizeOfVector2(): CInt = extern

  @name("rayscal_sizeof_Vector3")
  def SizeOfVector3(): CInt = extern

  @name("rayscal_sizeof_Vector4")
  def SizeOfVector4(): CInt = extern

  @name("rayscal_sizeof_Matrix")
  def SizeOfMatrix(): CInt = extern

  @name("rayscal_sizeof_Rectangle")
  def SizeOfRectangle(): CInt = extern

  @name("rayscal_sizeof_Image")
  def SizeOfImage(): CInt = extern

  @name("rayscal_sizeof_Texture2D")
  def SizeOfTexture2D(): CInt = extern

  @name("rayscal_sizeof_RenderTexture2D")
  def SizeOfRenderTexture2D(): CInt = extern

  @name("rayscal_sizeof_Shader")
  def SizeOfShader(): CInt = extern

  @name("rayscal_sizeof_Camera2D")
  def SizeOfCamera2D(): CInt = extern

  @name("rayscal_sizeof_Camera3D")
  def SizeOfCamera3D(): CInt = extern

  @name("rayscal_sizeof_Ray")
  def SizeOfRay(): CInt = extern

  @name("rayscal_sizeof_RayCollision")
  def SizeOfRayCollision(): CInt = extern

  @name("rayscal_sizeof_BoundingBox")
  def SizeOfBoundingBox(): CInt = extern

  @name("rayscal_sizeof_Wave")
  def SizeOfWave(): CInt = extern

  @name("rayscal_sizeof_AudioStream")
  def SizeOfAudioStream(): CInt = extern

  @name("rayscal_sizeof_Sound")
  def SizeOfSound(): CInt = extern

  @name("rayscal_sizeof_Music")
  def SizeOfMusic(): CInt = extern

  @name("rayscal_sizeof_FilePathList")
  def SizeOfFilePathList(): CInt = extern

  @name("rayscal_validate_struct_layout")
  def ValidateStructLayout(color: Ptr[Color], camera: Ptr[Camera2D], texture: Ptr[Texture2D], wave: Ptr[Wave], sound: Ptr[Sound]): CBool = extern

  @name("rayscal_LoadRenderTexture")
  def LoadRenderTexture(out: Ptr[RenderTexture2D], width: CInt, height: CInt): Unit = extern

  @name("rayscal_IsRenderTextureValid")
  def IsRenderTextureValid(target: Ptr[RenderTexture2D]): CBool = extern

  @name("rayscal_UnloadRenderTexture")
  def UnloadRenderTexture(target: Ptr[RenderTexture2D]): Unit = extern

  @name("rayscal_BeginMode2D")
  def BeginMode2D(camera: Ptr[Camera2D]): Unit = extern

  @name("rayscal_BeginMode3D")
  def BeginMode3D(camera: Ptr[Camera3D]): Unit = extern

  @name("rayscal_BeginTextureMode")
  def BeginTextureMode(target: Ptr[RenderTexture2D]): Unit = extern

  @name("rayscal_LoadShader")
  def LoadShader(out: Ptr[Shader], vsFileName: CString, fsFileName: CString): Unit = extern

  @name("rayscal_LoadShaderFromMemory")
  def LoadShaderFromMemory(out: Ptr[Shader], vsCode: CString, fsCode: CString): Unit = extern

  @name("rayscal_IsShaderValid")
  def IsShaderValid(shader: Ptr[Shader]): CBool = extern

  @name("rayscal_BeginShaderMode")
  def BeginShaderMode(shader: Ptr[Shader]): Unit = extern

  @name("rayscal_GetShaderLocation")
  def GetShaderLocation(shader: Ptr[Shader], uniformName: CString): CInt = extern

  @name("rayscal_GetShaderLocationAttrib")
  def GetShaderLocationAttrib(shader: Ptr[Shader], attribName: CString): CInt = extern

  @name("rayscal_SetShaderValueTexture")
  def SetShaderValueTexture(shader: Ptr[Shader], locIndex: CInt, texture: Ptr[Texture2D]): Unit = extern

  @name("rayscal_SetShaderValue")
  def SetShaderValue(shader: Ptr[Shader], locIndex: CInt, value: Ptr[Byte], uniformType: CInt): Unit = extern

  @name("rayscal_SetShaderValueMatrix")
  def SetShaderValueMatrix(shader: Ptr[Shader], locIndex: CInt, matrix: Ptr[Matrix]): Unit = extern

  @name("rayscal_UnloadShader")
  def UnloadShader(shader: Ptr[Shader]): Unit = extern

  @name("rayscal_GetScreenToWorld2D")
  def GetScreenToWorld2D(out: Ptr[Vector2], position: Ptr[Vector2], camera: Ptr[Camera2D]): Unit = extern

  @name("rayscal_GetWorldToScreen2D")
  def GetWorldToScreen2D(out: Ptr[Vector2], position: Ptr[Vector2], camera: Ptr[Camera2D]): Unit = extern

  @name("rayscal_GetScreenToWorldRay")
  def GetScreenToWorldRay(out: Ptr[Ray], position: Ptr[Vector2], camera: Ptr[Camera3D]): Unit = extern

  @name("rayscal_GetScreenToWorldRayEx")
  def GetScreenToWorldRayEx(out: Ptr[Ray], position: Ptr[Vector2], camera: Ptr[Camera3D], width: CInt, height: CInt): Unit = extern

  @name("rayscal_GetWorldToScreen")
  def GetWorldToScreen(out: Ptr[Vector2], position: Ptr[Vector3], camera: Ptr[Camera3D]): Unit = extern

  @name("rayscal_GetWorldToScreenEx")
  def GetWorldToScreenEx(out: Ptr[Vector2], position: Ptr[Vector3], camera: Ptr[Camera3D], width: CInt, height: CInt): Unit = extern

  @name("rayscal_UpdateCameraPro")
  def UpdateCameraPro(camera: Ptr[Camera3D], movement: Ptr[Vector3], rotation: Ptr[Vector3], zoom: CFloat): Unit = extern

  @name("rayscal_Fade")
  def Fade(out: Ptr[Color], color: Ptr[Color], alpha: CFloat): Unit = extern

  @name("rayscal_ColorTint")
  def ColorTint(out: Ptr[Color], color: Ptr[Color], tint: Ptr[Color]): Unit = extern

  @name("rayscal_ColorBrightness")
  def ColorBrightness(out: Ptr[Color], color: Ptr[Color], factor: CFloat): Unit = extern

  @name("rayscal_ColorFromHSV")
  def ColorFromHSV(out: Ptr[Color], hue: CFloat, saturation: CFloat, value: CFloat): Unit = extern

  @name("rayscal_ColorToHSV")
  def ColorToHSV(out: Ptr[Vector3], color: Ptr[Color]): Unit = extern

  @name("rayscal_ColorToInt")
  def ColorToInt(color: Ptr[Color]): CInt = extern

  @name("rayscal_ColorIsEqual")
  def ColorIsEqual(left: Ptr[Color], right: Ptr[Color]): CBool = extern

  @name("rayscal_ClearBackground")
  def ClearBackground(color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawText")
  def DrawText(text: CString, x: CInt, y: CInt, size: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawLine")
  def DrawLine(x1: CInt, y1: CInt, x2: CInt, y2: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawPixel")
  def DrawPixel(x: CInt, y: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawLineV")
  def DrawLineV(start: Ptr[Vector2], end: Ptr[Vector2], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawLineEx")
  def DrawLineEx(start: Ptr[Vector2], end: Ptr[Vector2], thick: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCircle")
  def DrawCircle(x: CInt, y: CInt, radius: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCircleV")
  def DrawCircleV(center: Ptr[Vector2], radius: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCircleLines")
  def DrawCircleLines(x: CInt, y: CInt, radius: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawEllipse")
  def DrawEllipse(x: CInt, y: CInt, radiusH: CFloat, radiusV: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawEllipseLines")
  def DrawEllipseLines(x: CInt, y: CInt, radiusH: CFloat, radiusV: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawRing")
  def DrawRing(center: Ptr[Vector2], innerRadius: CFloat, outerRadius: CFloat, startAngle: CFloat, endAngle: CFloat, segments: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawRectangle")
  def DrawRectangle(x: CInt, y: CInt, width: CInt, height: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawRectangleV")
  def DrawRectangleV(position: Ptr[Vector2], size: Ptr[Vector2], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawRectangleRec")
  def DrawRectangleRec(rec: Ptr[Rectangle], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawRectangleLines")
  def DrawRectangleLines(x: CInt, y: CInt, width: CInt, height: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawRectangleLinesEx")
  def DrawRectangleLinesEx(rec: Ptr[Rectangle], thick: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawTriangle")
  def DrawTriangle(v1: Ptr[Vector2], v2: Ptr[Vector2], v3: Ptr[Vector2], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawTriangleLines")
  def DrawTriangleLines(v1: Ptr[Vector2], v2: Ptr[Vector2], v3: Ptr[Vector2], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawPoly")
  def DrawPoly(center: Ptr[Vector2], sides: CInt, radius: CFloat, rotation: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawPolyLines")
  def DrawPolyLines(center: Ptr[Vector2], sides: CInt, radius: CFloat, rotation: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawPolyLinesEx")
  def DrawPolyLinesEx(center: Ptr[Vector2], sides: CInt, radius: CFloat, rotation: CFloat, thick: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawTexturePro")
  def DrawTexturePro(texture: Ptr[Texture2D], source: Ptr[Rectangle], dest: Ptr[Rectangle], origin: Ptr[Vector2], rotation: CFloat, tint: Ptr[Color]): Unit = extern

  @name("rayscal_LoadImage")
  def LoadImage(out: Ptr[Image], fileName: CString): Unit = extern

  @name("rayscal_ImageCopy")
  def ImageCopy(out: Ptr[Image], image: Ptr[Image]): Unit = extern

  @name("rayscal_ImageColorTint")
  def ImageColorTint(image: Ptr[Image], color: Ptr[Color]): Unit = extern

  @name("rayscal_ImageCrop")
  def ImageCrop(image: Ptr[Image], crop: Ptr[Rectangle]): Unit = extern

  @name("rayscal_IsImageValid")
  def IsImageValid(image: Ptr[Image]): CBool = extern

  @name("rayscal_UnloadImage")
  def UnloadImage(image: Ptr[Image]): Unit = extern

  @name("rayscal_GenImageColor")
  def GenImageColor(out: Ptr[Image], width: CInt, height: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_GenImageChecked")
  def GenImageChecked(out: Ptr[Image], width: CInt, height: CInt, checksX: CInt, checksY: CInt, col1: Ptr[Color], col2: Ptr[Color]): Unit = extern

  @name("rayscal_GenImageGradientLinear")
  def GenImageGradientLinear(out: Ptr[Image], width: CInt, height: CInt, direction: CInt, start: Ptr[Color], end: Ptr[Color]): Unit = extern

  @name("rayscal_GenImageGradientRadial")
  def GenImageGradientRadial(out: Ptr[Image], width: CInt, height: CInt, density: CFloat, inner: Ptr[Color], outer: Ptr[Color]): Unit = extern

  @name("rayscal_GenImagePerlinNoise")
  def GenImagePerlinNoise(out: Ptr[Image], width: CInt, height: CInt, offsetX: CInt, offsetY: CInt, scale: CFloat): Unit = extern

  @name("rayscal_GenImageCellular")
  def GenImageCellular(out: Ptr[Image], width: CInt, height: CInt, tileSize: CInt): Unit = extern

  @name("rayscal_LoadTexture")
  def LoadTexture(out: Ptr[Texture2D], fileName: CString): Unit = extern

  @name("rayscal_LoadTextureFromImage")
  def LoadTextureFromImage(out: Ptr[Texture2D], image: Ptr[Image]): Unit = extern

  @name("rayscal_LoadTextureCubemap")
  def LoadTextureCubemap(out: Ptr[TextureCubemap], image: Ptr[Image], layout: CInt): Unit = extern

  @name("rayscal_IsTextureValid")
  def IsTextureValid(texture: Ptr[Texture2D]): CBool = extern

  @name("rayscal_UnloadTexture")
  def UnloadTexture(texture: Ptr[Texture2D]): Unit = extern

  @name("rayscal_UpdateTexture")
  def UpdateTexture(texture: Ptr[Texture2D], pixels: Ptr[Byte]): Unit = extern

  @name("rayscal_UpdateTextureRec")
  def UpdateTextureRec(texture: Ptr[Texture2D], rec: Ptr[Rectangle], pixels: Ptr[Byte]): Unit = extern

  @name("rayscal_SetTextureFilter")
  def SetTextureFilter(texture: Ptr[Texture2D], filter: CInt): Unit = extern

  @name("rayscal_SetTextureWrap")
  def SetTextureWrap(texture: Ptr[Texture2D], wrap: CInt): Unit = extern

  @name("rayscal_DrawTexture")
  def DrawTexture(texture: Ptr[Texture2D], x: CInt, y: CInt, tint: Ptr[Color]): Unit = extern

  @name("rayscal_DrawTextureV")
  def DrawTextureV(texture: Ptr[Texture2D], position: Ptr[Vector2], tint: Ptr[Color]): Unit = extern

  @name("rayscal_DrawTextureEx")
  def DrawTextureEx(texture: Ptr[Texture2D], position: Ptr[Vector2], rotation: CFloat, scale: CFloat, tint: Ptr[Color]): Unit = extern

  @name("rayscal_DrawTextureRec")
  def DrawTextureRec(texture: Ptr[Texture2D], source: Ptr[Rectangle], position: Ptr[Vector2], tint: Ptr[Color]): Unit = extern

  @name("rayscal_DrawLine3D")
  def DrawLine3D(start: Ptr[Vector3], end: Ptr[Vector3], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawPoint3D")
  def DrawPoint3D(position: Ptr[Vector3], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCircle3D")
  def DrawCircle3D(center: Ptr[Vector3], radius: CFloat, rotationAxis: Ptr[Vector3], rotationAngle: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawTriangle3D")
  def DrawTriangle3D(v1: Ptr[Vector3], v2: Ptr[Vector3], v3: Ptr[Vector3], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCube")
  def DrawCube(position: Ptr[Vector3], width: CFloat, height: CFloat, length: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCubeV")
  def DrawCubeV(position: Ptr[Vector3], size: Ptr[Vector3], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCubeWires")
  def DrawCubeWires(position: Ptr[Vector3], width: CFloat, height: CFloat, length: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCubeWiresV")
  def DrawCubeWiresV(position: Ptr[Vector3], size: Ptr[Vector3], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawSphere")
  def DrawSphere(center: Ptr[Vector3], radius: CFloat, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawSphereEx")
  def DrawSphereEx(center: Ptr[Vector3], radius: CFloat, rings: CInt, slices: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawSphereWires")
  def DrawSphereWires(center: Ptr[Vector3], radius: CFloat, rings: CInt, slices: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCylinder")
  def DrawCylinder(position: Ptr[Vector3], radiusTop: CFloat, radiusBottom: CFloat, height: CFloat, slices: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCylinderEx")
  def DrawCylinderEx(start: Ptr[Vector3], end: Ptr[Vector3], startRadius: CFloat, endRadius: CFloat, sides: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCylinderWires")
  def DrawCylinderWires(position: Ptr[Vector3], radiusTop: CFloat, radiusBottom: CFloat, height: CFloat, slices: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCylinderWiresEx")
  def DrawCylinderWiresEx(start: Ptr[Vector3], end: Ptr[Vector3], startRadius: CFloat, endRadius: CFloat, sides: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCapsule")
  def DrawCapsule(start: Ptr[Vector3], end: Ptr[Vector3], radius: CFloat, slices: CInt, rings: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawCapsuleWires")
  def DrawCapsuleWires(start: Ptr[Vector3], end: Ptr[Vector3], radius: CFloat, slices: CInt, rings: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawPlane")
  def DrawPlane(center: Ptr[Vector3], size: Ptr[Vector2], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawRay")
  def DrawRay(ray: Ptr[Ray], color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawBoundingBox")
  def DrawBoundingBox(box: Ptr[BoundingBox], color: Ptr[Color]): Unit = extern

  @name("rayscal_CheckCollisionRecs")
  def CheckCollisionRecs(a: Ptr[Rectangle], b: Ptr[Rectangle]): CBool = extern

  @name("rayscal_CheckCollisionCircles")
  def CheckCollisionCircles(a: Ptr[Vector2], radiusA: CFloat, b: Ptr[Vector2], radiusB: CFloat): CBool = extern

  @name("rayscal_CheckCollisionCircleRec")
  def CheckCollisionCircleRec(center: Ptr[Vector2], radius: CFloat, rectangle: Ptr[Rectangle]): CBool = extern

  @name("rayscal_CheckCollisionPointRec")
  def CheckCollisionPointRec(point: Ptr[Vector2], rectangle: Ptr[Rectangle]): CBool = extern

  @name("rayscal_CheckCollisionPointCircle")
  def CheckCollisionPointCircle(point: Ptr[Vector2], center: Ptr[Vector2], radius: CFloat): CBool = extern

  @name("rayscal_CheckCollisionPointTriangle")
  def CheckCollisionPointTriangle(point: Ptr[Vector2], p1: Ptr[Vector2], p2: Ptr[Vector2], p3: Ptr[Vector2]): CBool = extern

  @name("rayscal_GetCollisionRec")
  def GetCollisionRec(out: Ptr[Rectangle], a: Ptr[Rectangle], b: Ptr[Rectangle]): Unit = extern

  @name("rayscal_CheckCollisionSpheres")
  def CheckCollisionSpheres(a: Ptr[Vector3], radiusA: CFloat, b: Ptr[Vector3], radiusB: CFloat): CBool = extern

  @name("rayscal_CheckCollisionBoxes")
  def CheckCollisionBoxes(a: Ptr[BoundingBox], b: Ptr[BoundingBox]): CBool = extern

  @name("rayscal_CheckCollisionBoxSphere")
  def CheckCollisionBoxSphere(box: Ptr[BoundingBox], center: Ptr[Vector3], radius: CFloat): CBool = extern

  @name("rayscal_GetRayCollisionSphere")
  def GetRayCollisionSphere(out: Ptr[RayCollision], ray: Ptr[Ray], center: Ptr[Vector3], radius: CFloat): Unit = extern

  @name("rayscal_GetRayCollisionBox")
  def GetRayCollisionBox(out: Ptr[RayCollision], ray: Ptr[Ray], box: Ptr[BoundingBox]): Unit = extern

  @name("rayscal_GetRayCollisionTriangle")
  def GetRayCollisionTriangle(out: Ptr[RayCollision], ray: Ptr[Ray], p1: Ptr[Vector3], p2: Ptr[Vector3], p3: Ptr[Vector3]): Unit = extern

  @name("rayscal_GetRayCollisionQuad")
  def GetRayCollisionQuad(out: Ptr[RayCollision], ray: Ptr[Ray], p1: Ptr[Vector3], p2: Ptr[Vector3], p3: Ptr[Vector3], p4: Ptr[Vector3]): Unit = extern

  @name("rayscal_GetRayCollisionModel")
  def GetRayCollisionModel(out: Ptr[RayCollision], ray: Ptr[Ray], model: Ptr[Byte]): Unit = extern

  @name("rayscal_LoadFont")
  def LoadFont(fileName: CString, fontSize: CInt): Ptr[Byte] = extern

  @name("rayscal_IsFontValid")
  def IsFontValid(font: Ptr[Byte]): CBool = extern

  @name("rayscal_UnloadFont")
  def UnloadFont(font: Ptr[Byte]): Unit = extern

  @name("rayscal_DrawTextEx")
  def DrawTextEx(font: Ptr[Byte], text: CString, position: Ptr[Vector2], fontSize: CFloat, spacing: CFloat, tint: Ptr[Color]): Unit = extern

  @name("rayscal_MeasureTextEx")
  def MeasureTextEx(out: Ptr[Vector2], font: Ptr[Byte], text: CString, fontSize: CFloat, spacing: CFloat): Unit = extern

  @name("rayscal_LoadWave")
  def LoadWave(out: Ptr[Wave], fileName: CString): Unit = extern

  @name("rayscal_IsWaveValid")
  def IsWaveValid(wave: Ptr[Wave]): CBool = extern

  @name("rayscal_UnloadWave")
  def UnloadWave(wave: Ptr[Wave]): Unit = extern

  @name("rayscal_LoadSound")
  def LoadSound(out: Ptr[Sound], fileName: CString): Unit = extern

  @name("rayscal_LoadSoundFromWave")
  def LoadSoundFromWave(out: Ptr[Sound], wave: Ptr[Wave]): Unit = extern

  @name("rayscal_LoadSoundAlias")
  def LoadSoundAlias(out: Ptr[Sound], source: Ptr[Sound]): Unit = extern

  @name("rayscal_IsSoundValid")
  def IsSoundValid(sound: Ptr[Sound]): CBool = extern

  @name("rayscal_UnloadSound")
  def UnloadSound(sound: Ptr[Sound]): Unit = extern

  @name("rayscal_UnloadSoundAlias")
  def UnloadSoundAlias(sound: Ptr[Sound]): Unit = extern

  @name("rayscal_PlaySound")
  def PlaySound(sound: Ptr[Sound]): Unit = extern

  @name("rayscal_StopSound")
  def StopSound(sound: Ptr[Sound]): Unit = extern

  @name("rayscal_PauseSound")
  def PauseSound(sound: Ptr[Sound]): Unit = extern

  @name("rayscal_ResumeSound")
  def ResumeSound(sound: Ptr[Sound]): Unit = extern

  @name("rayscal_IsSoundPlaying")
  def IsSoundPlaying(sound: Ptr[Sound]): CBool = extern

  @name("rayscal_SetSoundVolume")
  def SetSoundVolume(sound: Ptr[Sound], volume: CFloat): Unit = extern

  @name("rayscal_SetSoundPitch")
  def SetSoundPitch(sound: Ptr[Sound], pitch: CFloat): Unit = extern

  @name("rayscal_SetSoundPan")
  def SetSoundPan(sound: Ptr[Sound], pan: CFloat): Unit = extern

  @name("rayscal_LoadMusicStream")
  def LoadMusicStream(out: Ptr[Music], fileName: CString): Unit = extern

  @name("rayscal_IsMusicValid")
  def IsMusicValid(music: Ptr[Music]): CBool = extern

  @name("rayscal_UnloadMusicStream")
  def UnloadMusicStream(music: Ptr[Music]): Unit = extern

  @name("rayscal_PlayMusicStream")
  def PlayMusicStream(music: Ptr[Music]): Unit = extern

  @name("rayscal_UpdateMusicStream")
  def UpdateMusicStream(music: Ptr[Music]): Unit = extern

  @name("rayscal_StopMusicStream")
  def StopMusicStream(music: Ptr[Music]): Unit = extern

  @name("rayscal_PauseMusicStream")
  def PauseMusicStream(music: Ptr[Music]): Unit = extern

  @name("rayscal_ResumeMusicStream")
  def ResumeMusicStream(music: Ptr[Music]): Unit = extern

  @name("rayscal_IsMusicStreamPlaying")
  def IsMusicStreamPlaying(music: Ptr[Music]): CBool = extern

  @name("rayscal_SeekMusicStream")
  def SeekMusicStream(music: Ptr[Music], position: CFloat): Unit = extern

  @name("rayscal_SetMusicVolume")
  def SetMusicVolume(music: Ptr[Music], volume: CFloat): Unit = extern

  @name("rayscal_SetMusicPitch")
  def SetMusicPitch(music: Ptr[Music], pitch: CFloat): Unit = extern

  @name("rayscal_SetMusicPan")
  def SetMusicPan(music: Ptr[Music], pan: CFloat): Unit = extern

  @name("rayscal_GetMusicTimeLength")
  def GetMusicTimeLength(music: Ptr[Music]): CFloat = extern

  @name("rayscal_GetMusicTimePlayed")
  def GetMusicTimePlayed(music: Ptr[Music]): CFloat = extern

  @name("rayscal_LoadModel")
  def LoadModel(fileName: CString): Ptr[Byte] = extern

  @name("rayscal_GenModelCube")
  def GenModelCube(width: CFloat, height: CFloat, length: CFloat): Ptr[Byte] = extern

  @name("rayscal_GenModelSphere")
  def GenModelSphere(radius: CFloat, rings: CInt, slices: CInt): Ptr[Byte] = extern

  @name("rayscal_GenModelPlane")
  def GenModelPlane(width: CFloat, length: CFloat, resolutionX: CInt, resolutionZ: CInt): Ptr[Byte] = extern

  @name("rayscal_GenModelCylinder")
  def GenModelCylinder(radius: CFloat, height: CFloat, slices: CInt): Ptr[Byte] = extern

  @name("rayscal_GenModelTorus")
  def GenModelTorus(radius: CFloat, size: CFloat, radialSegments: CInt, sides: CInt): Ptr[Byte] = extern

  @name("rayscal_GenModelKnot")
  def GenModelKnot(radius: CFloat, size: CFloat, radialSegments: CInt, sides: CInt): Ptr[Byte] = extern

  @name("rayscal_IsModelValid")
  def IsModelValid(model: Ptr[Byte]): CBool = extern

  @name("rayscal_UnloadModel")
  def UnloadModel(model: Ptr[Byte]): Unit = extern

  @name("rayscal_DrawModel")
  def DrawModel(model: Ptr[Byte], position: Ptr[Vector3], scale: CFloat, tint: Ptr[Color]): Unit = extern

  @name("rayscal_DrawModelEx")
  def DrawModelEx(model: Ptr[Byte], position: Ptr[Vector3], rotationAxis: Ptr[Vector3], rotationAngle: CFloat, scale: Ptr[Vector3], tint: Ptr[Color]): Unit = extern

  @name("rayscal_DrawModelWires")
  def DrawModelWires(model: Ptr[Byte], position: Ptr[Vector3], scale: CFloat, tint: Ptr[Color]): Unit = extern

  @name("rayscal_GetModelBoundingBox")
  def GetModelBoundingBox(out: Ptr[BoundingBox], model: Ptr[Byte]): Unit = extern

  @name("rayscal_ModelMeshCount")
  def ModelMeshCount(model: Ptr[Byte]): CInt = extern

  @name("rayscal_ModelMaterialCount")
  def ModelMaterialCount(model: Ptr[Byte]): CInt = extern

  @name("rayscal_ModelSetMaterialShader")
  def ModelSetMaterialShader(model: Ptr[Byte], material: CInt, shader: Ptr[Shader]): CBool = extern

  @name("rayscal_ModelSetMaterialColor")
  def ModelSetMaterialColor(model: Ptr[Byte], material: CInt, map: CInt, color: Ptr[Color]): CBool = extern

  @name("rayscal_ModelSetMaterialValue")
  def ModelSetMaterialValue(model: Ptr[Byte], material: CInt, map: CInt, value: CFloat): CBool = extern

  @name("rayscal_ModelSetMaterialTexture")
  def ModelSetMaterialTexture(model: Ptr[Byte], material: CInt, map: CInt, texture: Ptr[Texture2D]): CBool = extern

  @name("rayscal_LoadDroppedFiles")
  def LoadDroppedFiles(): Ptr[Byte] = extern

  @name("rayscal_DroppedFileCount")
  def DroppedFileCount(files: Ptr[Byte]): CUnsignedInt = extern

  @name("rayscal_DroppedFilePath")
  def DroppedFilePath(files: Ptr[Byte], index: CUnsignedInt): CString = extern

  @name("rayscal_UnloadDroppedFiles")
  def UnloadDroppedFiles(files: Ptr[Byte]): Unit = extern

  @name("rayscal_GetMouseDelta")
  def GetMouseDelta(out: Ptr[Vector2]): Unit = extern

  @name("rayscal_GetMouseWheelMoveV")
  def GetMouseWheelMoveV(out: Ptr[Vector2]): Unit = extern

  @name("rayscal_GetTouchPosition")
  def GetTouchPosition(out: Ptr[Vector2], index: CInt): Unit = extern

  @name("rayscal_GetGestureDragVector")
  def GetGestureDragVector(out: Ptr[Vector2]): Unit = extern

  @name("rayscal_GetGesturePinchVector")
  def GetGesturePinchVector(out: Ptr[Vector2]): Unit = extern

  @name("rayscal_GetWindowPosition")
  def GetWindowPosition(out: Ptr[Vector2]): Unit = extern

  @name("rayscal_GetWindowScaleDPI")
  def GetWindowScaleDPI(out: Ptr[Vector2]): Unit = extern

  @name("rayscal_GetMonitorPosition")
  def GetMonitorPosition(out: Ptr[Vector2], monitor: CInt): Unit = extern

  @name("rayscal_GetCameraMatrix")
  def GetCameraMatrix(out: Ptr[Matrix], camera: Ptr[Camera3D]): Unit = extern

  @name("rayscal_GetCameraMatrix2D")
  def GetCameraMatrix2D(out: Ptr[Matrix], camera: Ptr[Camera2D]): Unit = extern
