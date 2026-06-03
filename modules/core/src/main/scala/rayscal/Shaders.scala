package rayscal

import rayscal.raw.Raylib
import scala.scalanative.unsafe.*

object Shaders:
  def load(vertexShaderFile: String, fragmentShaderFile: String): Shader =
    Zone:
      Raylib.LoadShader(toCString(vertexShaderFile), toCString(fragmentShaderFile))

  def fromMemory(vertexShaderCode: String, fragmentShaderCode: String): Shader =
    Zone:
      Raylib.LoadShaderFromMemory(toCString(vertexShaderCode), toCString(fragmentShaderCode))

  def isValid(shader: Shader): Boolean =
    Raylib.IsShaderValid(shader)

  def unload(shader: Shader): Unit =
    Raylib.UnloadShader(shader)

  def withShader(vertexShaderFile: String, fragmentShaderFile: String)(body: Shader => Unit): Unit =
    val shader = load(vertexShaderFile, fragmentShaderFile)
    try body(shader)
    finally unload(shader)

  def withShaderFromMemory(vertexShaderCode: String, fragmentShaderCode: String)(body: Shader => Unit): Unit =
    val shader = fromMemory(vertexShaderCode, fragmentShaderCode)
    try body(shader)
    finally unload(shader)

  def location(shader: Shader, uniformName: String): Int =
    Zone:
      Raylib.GetShaderLocation(shader, toCString(uniformName))

  def attributeLocation(shader: Shader, attributeName: String): Int =
    Zone:
      Raylib.GetShaderLocationAttrib(shader, toCString(attributeName))

  def setTexture(shader: Shader, location: Int, texture: Texture2D): Unit =
    Raylib.SetShaderValueTexture(shader, location, texture)

  def id(shader: Shader): Int =
    shader._1.toInt
