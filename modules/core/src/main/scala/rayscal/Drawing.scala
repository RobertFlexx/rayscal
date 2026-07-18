package rayscal

import rayscal.raw.{Color, Raylib, RayscalNative, Vector2}
import scala.scalanative.unsafe.*

object Drawing:
  def frame(body: Zone ?=> Unit): Unit =
    Zone:
      Raylib.BeginDrawing()
      try body
      finally Raylib.EndDrawing()

  def mode2D(camera: Camera2D)(body: Zone ?=> Unit): Unit =
    Zone:
      RayscalNative.BeginMode2D(NativeCopies.camera2D(camera))
      try body
      finally Raylib.EndMode2D()

  def mode3D(camera: Camera3D)(body: Zone ?=> Unit): Unit =
    Zone:
      RayscalNative.BeginMode3D(NativeCopies.camera3D(camera))
      try body
      finally Raylib.EndMode3D()

  def textureMode(target: RenderTexture2D)(body: Zone ?=> Unit): Unit =
    Zone:
      target.requireLive()
      RayscalNative.BeginTextureMode(target.ptr)
      try body
      finally Raylib.EndTextureMode()

  def shaderMode(shader: Shader)(body: Zone ?=> Unit): Unit =
    Zone:
      shader.requireLive()
      RayscalNative.BeginShaderMode(shader.ptr)
      try body
      finally Raylib.EndShaderMode()

  def blend(mode: Int)(body: Zone ?=> Unit): Unit =
    Zone:
      Raylib.BeginBlendMode(mode)
      try body
      finally Raylib.EndBlendMode()

  def scissor(x: Int, y: Int, width: Int, height: Int)(body: Zone ?=> Unit): Unit =
    Zone:
      Raylib.BeginScissorMode(x, y, width, height)
      try body
      finally Raylib.EndScissorMode()

  def clear(color: Color): Unit =
    Zone:
      RayscalNative.ClearBackground(NativeCopies.color(color))

  def text(value: String, x: Int, y: Int, size: Int, color: Color)(using Zone): Unit =
    RayscalNative.DrawText(toCString(value), x, y, size, NativeCopies.color(color))

  def text(font: Font, value: String, position: Vector2, size: Float, spacing: Float, color: Color)(using Zone): Unit =
    Fonts.draw(font, value, position, size, spacing, color)

  def fps(x: Int, y: Int): Unit =
    Raylib.DrawFPS(x, y)

  def measureText(value: String, size: Int)(using Zone): Int =
    Raylib.MeasureText(toCString(value), size)

  def circle(x: Int, y: Int, radius: Float, color: Color): Unit =
    Shapes.circle(x, y, radius, color)

  def circle(center: Vector2, radius: Float, color: Color): Unit =
    Shapes.circle(center, radius, color)
