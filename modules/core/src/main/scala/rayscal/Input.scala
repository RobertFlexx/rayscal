package rayscal

import rayscal.raw.{Raylib, RayscalNative}
import scala.scalanative.unsafe.Zone
import scala.scalanative.unsafe.fromCString
import scala.scalanative.unsafe.toCString
import scala.scalanative.unsafe.stackalloc
import scala.scalanative.unsigned.*

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
  val P: Int = 80
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
    val out = stackalloc[Vector2]()
    RayscalNative.GetMouseDelta(out)
    !out

  def setPosition(x: Int, y: Int): Unit =
    Raylib.SetMousePosition(x, y)

  def setOffset(x: Int, y: Int): Unit =
    Raylib.SetMouseOffset(x, y)

  def setScale(x: Float, y: Float): Unit =
    Raylib.SetMouseScale(x, y)

  def wheel: Float =
    Raylib.GetMouseWheelMove()

  def wheelVector: Vector2 =
    val out = stackalloc[Vector2]()
    RayscalNative.GetMouseWheelMoveV(out)
    !out

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

object GamepadButtons:
  val Unknown: Int = 0
  val LeftFaceUp: Int = 1
  val LeftFaceRight: Int = 2
  val LeftFaceDown: Int = 3
  val LeftFaceLeft: Int = 4
  val RightFaceUp: Int = 5
  val RightFaceRight: Int = 6
  val RightFaceDown: Int = 7
  val RightFaceLeft: Int = 8
  val LeftTrigger1: Int = 9
  val LeftTrigger2: Int = 10
  val RightTrigger1: Int = 11
  val RightTrigger2: Int = 12
  val MiddleLeft: Int = 13
  val Middle: Int = 14
  val MiddleRight: Int = 15
  val LeftThumb: Int = 16
  val RightThumb: Int = 17

object GamepadAxes:
  val LeftX: Int = 0
  val LeftY: Int = 1
  val RightX: Int = 2
  val RightY: Int = 3
  val LeftTrigger: Int = 4
  val RightTrigger: Int = 5

object Gamepads:
  def isAvailable(gamepad: Int): Boolean =
    Raylib.IsGamepadAvailable(gamepad)

  def name(gamepad: Int): String =
    val value = Raylib.GetGamepadName(gamepad)
    if value == null then "" else fromCString(value)

  def isPressed(gamepad: Int, button: Int): Boolean =
    Raylib.IsGamepadButtonPressed(gamepad, button)

  def isDown(gamepad: Int, button: Int): Boolean =
    Raylib.IsGamepadButtonDown(gamepad, button)

  def isReleased(gamepad: Int, button: Int): Boolean =
    Raylib.IsGamepadButtonReleased(gamepad, button)

  def isUp(gamepad: Int, button: Int): Boolean =
    Raylib.IsGamepadButtonUp(gamepad, button)

  def buttonPressed: Int =
    Raylib.GetGamepadButtonPressed()

  def axisCount(gamepad: Int): Int =
    Raylib.GetGamepadAxisCount(gamepad)

  def axisMovement(gamepad: Int, axis: Int): Float =
    Raylib.GetGamepadAxisMovement(gamepad, axis)

  def setMappings(mappings: String): Int =
    Zone:
      Raylib.SetGamepadMappings(toCString(mappings))

object Touch:
  def x: Int =
    Raylib.GetTouchX()

  def y: Int =
    Raylib.GetTouchY()

  def position(index: Int): Vector2 =
    val out = stackalloc[Vector2]()
    RayscalNative.GetTouchPosition(out, index)
    !out

  def id(index: Int): Int =
    Raylib.GetTouchPointId(index)

  def count: Int =
    Raylib.GetTouchPointCount()

object Gestures:
  val None: Int = 0
  val Tap: Int = 1
  val Doubletap: Int = 2
  val Hold: Int = 4
  val Drag: Int = 8
  val SwipeRight: Int = 16
  val SwipeLeft: Int = 32
  val SwipeUp: Int = 64
  val SwipeDown: Int = 128
  val PinchIn: Int = 256
  val PinchOut: Int = 512

  def setEnabled(flags: Int): Unit =
    Raylib.SetGesturesEnabled(flags.toUInt)

  def isDetected(gesture: Int): Boolean =
    Raylib.IsGestureDetected(gesture.toUInt)

  def detected: Int =
    Raylib.GetGestureDetected()

  def holdDuration: Float =
    Raylib.GetGestureHoldDuration()

  def dragVector: Vector2 =
    val out = stackalloc[Vector2]()
    RayscalNative.GetGestureDragVector(out)
    !out

  def dragAngle: Float =
    Raylib.GetGestureDragAngle()

  def pinchVector: Vector2 =
    val out = stackalloc[Vector2]()
    RayscalNative.GetGesturePinchVector(out)
    !out

  def pinchAngle: Float =
    Raylib.GetGesturePinchAngle()
