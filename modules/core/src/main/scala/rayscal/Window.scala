package rayscal

import rayscal.raw.Raylib
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

object Window:
  def withWindow(width: Int, height: Int, title: String)(body: Zone ?=> Unit): Unit =
    Zone:
      Raylib.InitWindow(width, height, toCString(title))
      try body
      finally Raylib.CloseWindow()

  def shouldClose: Boolean =
    Raylib.WindowShouldClose()

  def isReady: Boolean =
    Raylib.IsWindowReady()

  def isFullscreen: Boolean =
    Raylib.IsWindowFullscreen()

  def isFocused: Boolean =
    Raylib.IsWindowFocused()

  def isResized: Boolean =
    Raylib.IsWindowResized()

  def setTitle(title: String): Unit =
    Zone:
      Raylib.SetWindowTitle(toCString(title))

  def setSize(width: Int, height: Int): Unit =
    Raylib.SetWindowSize(width, height)

  def setPosition(x: Int, y: Int): Unit =
    Raylib.SetWindowPosition(x, y)

  def setOpacity(opacity: Float): Unit =
    Raylib.SetWindowOpacity(opacity)

  def toggleFullscreen(): Unit =
    Raylib.ToggleFullscreen()

  def maximize(): Unit =
    Raylib.MaximizeWindow()

  def minimize(): Unit =
    Raylib.MinimizeWindow()

  def restore(): Unit =
    Raylib.RestoreWindow()

  def setTargetFps(fps: Int): Unit =
    Raylib.SetTargetFPS(fps)

  def screenWidth: Int =
    Raylib.GetScreenWidth()

  def screenHeight: Int =
    Raylib.GetScreenHeight()

  def renderWidth: Int =
    Raylib.GetRenderWidth()

  def renderHeight: Int =
    Raylib.GetRenderHeight()

object WindowFlags:
  val Resizable: Int = 0x00000004
  val Undecorated: Int = 0x00000008
  val Hidden: Int = 0x00000080
  val Minimized: Int = 0x00000200
  val Maximized: Int = 0x00000400
  val Unfocused: Int = 0x00000800
  val Topmost: Int = 0x00001000
  val AlwaysRun: Int = 0x00000100
  val Transparent: Int = 0x00000010
  val HighDpi: Int = 0x00002000
  val MousePassthrough: Int = 0x00004000

object Config:
  def setFlags(flags: Int): Unit =
    Raylib.SetConfigFlags(flags.toUInt)

  def setTraceLogLevel(level: Int): Unit =
    Raylib.SetTraceLogLevel(level)
