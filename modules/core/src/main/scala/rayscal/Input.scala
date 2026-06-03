package rayscal

import rayscal.raw.Raylib
import scala.scalanative.unsafe.Zone

object Keys:
  // Values mirror raylib's KeyboardKey enum.
  val Right: Int = 262
  val Left: Int = 263
  val Down: Int = 264
  val Up: Int = 265
  val Space: Int = 32
  val Escape: Int = 256
  val Enter: Int = 257
  val Tab: Int = 258
  val Backspace: Int = 259
  val A: Int = 65
  val D: Int = 68
  val S: Int = 83
  val W: Int = 87

object Keyboard:
  def isDown(key: Int): Boolean =
    Raylib.IsKeyDown(key)

  def isPressed(key: Int): Boolean =
    Raylib.IsKeyPressed(key)

  def isPressedRepeat(key: Int): Boolean =
    Raylib.IsKeyPressedRepeat(key)

  def isReleased(key: Int): Boolean =
    Raylib.IsKeyReleased(key)

  def isUp(key: Int): Boolean =
    Raylib.IsKeyUp(key)

  def getPressed: Int =
    Raylib.GetKeyPressed()

  def getCharPressed: Int =
    Raylib.GetCharPressed()

  def setExitKey(key: Int): Unit =
    Raylib.SetExitKey(key)

object MouseButtons:
  val Left: Int = 0
  val Right: Int = 1
  val Middle: Int = 2
  val Side: Int = 3
  val Extra: Int = 4
  val Forward: Int = 5
  val Back: Int = 6

object Mouse:
  def isPressed(button: Int): Boolean =
    Raylib.IsMouseButtonPressed(button)

  def isDown(button: Int): Boolean =
    Raylib.IsMouseButtonDown(button)

  def isReleased(button: Int): Boolean =
    Raylib.IsMouseButtonReleased(button)

  def isUp(button: Int): Boolean =
    Raylib.IsMouseButtonUp(button)

  def x: Int =
    Raylib.GetMouseX()

  def y: Int =
    Raylib.GetMouseY()

  def position(using Zone): Vector2 =
    Vector.vector2(x.toFloat, y.toFloat)

  def delta: Vector2 =
    Raylib.GetMouseDelta()

  def setPosition(x: Int, y: Int): Unit =
    Raylib.SetMousePosition(x, y)

  def setOffset(x: Int, y: Int): Unit =
    Raylib.SetMouseOffset(x, y)

  def setScale(x: Float, y: Float): Unit =
    Raylib.SetMouseScale(x, y)

  def wheel: Float =
    Raylib.GetMouseWheelMove()

  def wheelVector: Vector2 =
    Raylib.GetMouseWheelMoveV()

object Cursor:
  def show(): Unit =
    Raylib.ShowCursor()

  def hide(): Unit =
    Raylib.HideCursor()

  def isHidden: Boolean =
    Raylib.IsCursorHidden()

  def enable(): Unit =
    Raylib.EnableCursor()

  def disable(): Unit =
    Raylib.DisableCursor()

  def isOnScreen: Boolean =
    Raylib.IsCursorOnScreen()
