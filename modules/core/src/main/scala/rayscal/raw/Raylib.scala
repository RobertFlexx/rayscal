package rayscal.raw

import scala.scalanative.unsafe.*

/** Safe raylib @extern bindings.
  *
  * Only primitives, enums, C strings, callbacks, and pointers are allowed.
  * Struct-by-value arguments and returns are intentionally absent; use
  * [[RayscalNative]] pointer shims instead.
  */
@extern
@link("raylib")
object Raylib:
  def InitWindow(width: CInt, height: CInt, title: CString): Unit = extern
  def CloseWindow(): Unit = extern
  def WindowShouldClose(): CBool = extern
  def IsWindowReady(): CBool = extern
  def IsWindowFullscreen(): CBool = extern
  def IsWindowHidden(): CBool = extern
  def IsWindowMinimized(): CBool = extern
  def IsWindowMaximized(): CBool = extern
  def IsWindowFocused(): CBool = extern
  def IsWindowResized(): CBool = extern
  def IsWindowState(flag: CUnsignedInt): CBool = extern
  def SetWindowState(flags: CUnsignedInt): Unit = extern
  def ClearWindowState(flags: CUnsignedInt): Unit = extern
  def ToggleFullscreen(): Unit = extern
  def ToggleBorderlessWindowed(): Unit = extern
  def MaximizeWindow(): Unit = extern
  def MinimizeWindow(): Unit = extern
  def RestoreWindow(): Unit = extern
  def SetWindowTitle(title: CString): Unit = extern
  def SetWindowPosition(x: CInt, y: CInt): Unit = extern
  def SetWindowMonitor(monitor: CInt): Unit = extern
  def SetWindowMinSize(width: CInt, height: CInt): Unit = extern
  def SetWindowMaxSize(width: CInt, height: CInt): Unit = extern
  def SetWindowSize(width: CInt, height: CInt): Unit = extern
  def SetWindowOpacity(opacity: CFloat): Unit = extern
  def SetWindowFocused(): Unit = extern
  def GetMonitorCount(): CInt = extern
  def GetCurrentMonitor(): CInt = extern
  def GetMonitorWidth(monitor: CInt): CInt = extern
  def GetMonitorHeight(monitor: CInt): CInt = extern
  def GetMonitorPhysicalWidth(monitor: CInt): CInt = extern
  def GetMonitorPhysicalHeight(monitor: CInt): CInt = extern
  def GetMonitorRefreshRate(monitor: CInt): CInt = extern

  def BeginDrawing(): Unit = extern
  def EndDrawing(): Unit = extern
  def EndMode2D(): Unit = extern
  def EndMode3D(): Unit = extern
  def EndTextureMode(): Unit = extern
  def EndShaderMode(): Unit = extern
  def BeginBlendMode(mode: CInt): Unit = extern
  def EndBlendMode(): Unit = extern
  def BeginScissorMode(x: CInt, y: CInt, width: CInt, height: CInt): Unit = extern
  def EndScissorMode(): Unit = extern

  def DrawFPS(posX: CInt, posY: CInt): Unit = extern
  def SetTextLineSpacing(spacing: CInt): Unit = extern
  def MeasureText(text: CString, fontSize: CInt): CInt = extern

  def SetTargetFPS(fps: CInt): Unit = extern
  def GetFrameTime(): CFloat = extern
  def GetTime(): CDouble = extern
  def GetFPS(): CInt = extern
  def GetScreenWidth(): CInt = extern
  def GetScreenHeight(): CInt = extern
  def GetRenderWidth(): CInt = extern
  def GetRenderHeight(): CInt = extern
  def SetRandomSeed(seed: CUnsignedInt): Unit = extern
  def GetRandomValue(min: CInt, max: CInt): CInt = extern
  def TakeScreenshot(fileName: CString): Unit = extern
  def SetConfigFlags(flags: CUnsignedInt): Unit = extern
  def SetTraceLogLevel(logLevel: CInt): Unit = extern

  def IsKeyDown(key: CInt): CBool = extern
  def IsKeyPressed(key: CInt): CBool = extern
  def IsKeyPressedRepeat(key: CInt): CBool = extern
  def IsKeyReleased(key: CInt): CBool = extern
  def IsKeyUp(key: CInt): CBool = extern
  def GetKeyPressed(): CInt = extern
  def GetCharPressed(): CInt = extern
  def SetExitKey(key: CInt): Unit = extern

  def IsMouseButtonPressed(button: CInt): CBool = extern
  def IsMouseButtonDown(button: CInt): CBool = extern
  def IsMouseButtonReleased(button: CInt): CBool = extern
  def IsMouseButtonUp(button: CInt): CBool = extern
  def GetMouseX(): CInt = extern
  def GetMouseY(): CInt = extern
  def SetMousePosition(x: CInt, y: CInt): Unit = extern
  def SetMouseOffset(offsetX: CInt, offsetY: CInt): Unit = extern
  def SetMouseScale(scaleX: CFloat, scaleY: CFloat): Unit = extern
  def GetMouseWheelMove(): CFloat = extern
  def SetMouseCursor(cursor: CInt): Unit = extern
  def ShowCursor(): Unit = extern
  def HideCursor(): Unit = extern
  def IsCursorHidden(): CBool = extern
  def EnableCursor(): Unit = extern
  def DisableCursor(): Unit = extern
  def IsCursorOnScreen(): CBool = extern

  def IsGamepadAvailable(gamepad: CInt): CBool = extern
  def GetGamepadName(gamepad: CInt): CString = extern
  def IsGamepadButtonPressed(gamepad: CInt, button: CInt): CBool = extern
  def IsGamepadButtonDown(gamepad: CInt, button: CInt): CBool = extern
  def IsGamepadButtonReleased(gamepad: CInt, button: CInt): CBool = extern
  def IsGamepadButtonUp(gamepad: CInt, button: CInt): CBool = extern
  def GetGamepadButtonPressed(): CInt = extern
  def GetGamepadAxisCount(gamepad: CInt): CInt = extern
  def GetGamepadAxisMovement(gamepad: CInt, axis: CInt): CFloat = extern
  def SetGamepadMappings(mappings: CString): CInt = extern

  def GetTouchX(): CInt = extern
  def GetTouchY(): CInt = extern
  def GetTouchPointId(index: CInt): CInt = extern
  def GetTouchPointCount(): CInt = extern

  def SetGesturesEnabled(flags: CUnsignedInt): Unit = extern
  def IsGestureDetected(gesture: CUnsignedInt): CBool = extern
  def GetGestureDetected(): CInt = extern
  def GetGestureHoldDuration(): CFloat = extern
  def GetGestureDragAngle(): CFloat = extern
  def GetGesturePinchAngle(): CFloat = extern

  def ImageResize(image: Ptr[Image], newWidth: CInt, newHeight: CInt): Unit = extern
  def ImageResizeNN(image: Ptr[Image], newWidth: CInt, newHeight: CInt): Unit = extern
  def ImageMipmaps(image: Ptr[Image]): Unit = extern
  def ImageFlipVertical(image: Ptr[Image]): Unit = extern
  def ImageFlipHorizontal(image: Ptr[Image]): Unit = extern
  def ImageRotate(image: Ptr[Image], degrees: CInt): Unit = extern
  def ImageColorInvert(image: Ptr[Image]): Unit = extern
  def ImageColorGrayscale(image: Ptr[Image]): Unit = extern
  def ImageColorContrast(image: Ptr[Image], contrast: CFloat): Unit = extern
  def ImageColorBrightness(image: Ptr[Image], brightness: CInt): Unit = extern

  def GenTextureMipmaps(texture: Ptr[Texture2D]): Unit = extern

  def UpdateCamera(camera: Ptr[Camera3D], mode: CInt): Unit = extern

  def DrawGrid(slices: CInt, spacing: CFloat): Unit = extern

  def InitAudioDevice(): Unit = extern
  def CloseAudioDevice(): Unit = extern
  def IsAudioDeviceReady(): CBool = extern
  def SetMasterVolume(volume: CFloat): Unit = extern
  def GetMasterVolume(): CFloat = extern

  def FileExists(fileName: CString): CBool = extern
  def DirectoryExists(dirPath: CString): CBool = extern
  def IsPathFile(path: CString): CBool = extern
  def GetFileLength(fileName: CString): CInt = extern
  def GetFileExtension(fileName: CString): CString = extern
  def GetFileName(filePath: CString): CString = extern
  def GetFileNameWithoutExt(filePath: CString): CString = extern
  def GetDirectoryPath(filePath: CString): CString = extern
  def GetPrevDirectoryPath(dirPath: CString): CString = extern
  def GetWorkingDirectory(): CString = extern
  def GetApplicationDirectory(): CString = extern
  def ChangeDirectory(dir: CString): CBool = extern
  def IsFileDropped(): CBool = extern
