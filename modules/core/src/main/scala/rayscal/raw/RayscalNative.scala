package rayscal.raw

import scala.scalanative.unsafe.*

@extern
object RayscalNative:
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

  @name("rayscal_GetScreenToWorld2D")
  def GetScreenToWorld2D(out: Ptr[Vector2], position: Ptr[Vector2], camera: Ptr[Camera2D]): Unit = extern

  @name("rayscal_GetWorldToScreen2D")
  def GetWorldToScreen2D(out: Ptr[Vector2], position: Ptr[Vector2], camera: Ptr[Camera2D]): Unit = extern

  @name("rayscal_ClearBackground")
  def ClearBackground(color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawText")
  def DrawText(text: CString, x: CInt, y: CInt, size: CInt, color: Ptr[Color]): Unit = extern

  @name("rayscal_DrawLine")
  def DrawLine(x1: CInt, y1: CInt, x2: CInt, y2: CInt, color: Ptr[Color]): Unit = extern

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
