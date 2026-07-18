package rayscal

import rayscal.raw.RayscalNative
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

object RaylibAbi:
  private var validated = false

  def raylibVersion: String =
    s"${RayscalNative.RaylibVersionMajor()}.${RayscalNative.RaylibVersionMinor()}.${RayscalNative.RaylibVersionPatch()}"

  def validate(): Unit =
    if !validated then
      val major = RayscalNative.RaylibVersionMajor()
      val minor = RayscalNative.RaylibVersionMinor()
      if major != 6 || minor != 0 then
        throw new IllegalStateException(
          s"Unsupported raylib version $raylibVersion; rayscal 0.1 targets raylib 6.0.x"
        )
      check("Color", sizeof[Color].toInt, RayscalNative.SizeOfColor())
      check("Vector2", sizeof[Vector2].toInt, RayscalNative.SizeOfVector2())
      check("Vector3", sizeof[Vector3].toInt, RayscalNative.SizeOfVector3())
      check("Vector4", sizeof[raw.Vector4].toInt, RayscalNative.SizeOfVector4())
      check("Matrix", sizeof[raw.Matrix].toInt, RayscalNative.SizeOfMatrix())
      check("Rectangle", sizeof[Rectangle].toInt, RayscalNative.SizeOfRectangle())
      check("Image", sizeof[raw.Image].toInt, RayscalNative.SizeOfImage())
      check("Texture2D", sizeof[raw.Texture2D].toInt, RayscalNative.SizeOfTexture2D())
      check("RenderTexture2D", sizeof[raw.RenderTexture2D].toInt, RayscalNative.SizeOfRenderTexture2D())
      check("Shader", sizeof[raw.Shader].toInt, RayscalNative.SizeOfShader())
      check("Camera2D", sizeof[Camera2D].toInt, RayscalNative.SizeOfCamera2D())
      check("Camera3D", sizeof[Camera3D].toInt, RayscalNative.SizeOfCamera3D())
      check("Ray", sizeof[Ray].toInt, RayscalNative.SizeOfRay())
      check("RayCollision", sizeof[RayCollision].toInt, RayscalNative.SizeOfRayCollision())
      check("BoundingBox", sizeof[BoundingBox].toInt, RayscalNative.SizeOfBoundingBox())
      check("Wave", sizeof[raw.Wave].toInt, RayscalNative.SizeOfWave())
      check("AudioStream", sizeof[raw.AudioStream].toInt, RayscalNative.SizeOfAudioStream())
      check("Sound", sizeof[raw.Sound].toInt, RayscalNative.SizeOfSound())
      check("Music", sizeof[raw.Music].toInt, RayscalNative.SizeOfMusic())
      check("FilePathList", sizeof[raw.FilePathList].toInt, RayscalNative.SizeOfFilePathList())
      validateStructLayout()
      validated = true

  private def check(name: String, scalaSize: Int, nativeSize: Int): Unit =
    if scalaSize != nativeSize then
      throw new IllegalStateException(
        s"raylib ABI mismatch for $name: Scala Native size=$scalaSize, raylib C size=$nativeSize"
      )

  private def validateStructLayout(): Unit =
    Zone:
      val color = stackalloc[Color]()
      (!color)._1 = 11.toUByte
      (!color)._2 = 22.toUByte
      (!color)._3 = 33.toUByte
      (!color)._4 = 44.toUByte

      val camera = stackalloc[Camera2D]()
      (!camera)._1 = 1.25f
      (!camera)._2 = -2.5f
      (!camera)._3 = 3.75f
      (!camera)._4 = -4.5f
      (!camera)._5 = 12.5f
      (!camera)._6 = 2.0f

      val texture = stackalloc[raw.Texture2D]()
      (!texture)._1 = 123.toUInt
      (!texture)._2 = 456
      (!texture)._3 = 789
      (!texture)._4 = 3
      (!texture)._5 = 7

      val wave = stackalloc[raw.Wave]()
      (!wave)._1 = 101.toUInt
      (!wave)._2 = 44100.toUInt
      (!wave)._3 = 16.toUInt
      (!wave)._4 = 2.toUInt
      (!wave)._5 = null

      val stream = stackalloc[AudioStream]()
      (!stream)._1 = null
      (!stream)._2 = null
      (!stream)._3 = 48000.toUInt
      (!stream)._4 = 32.toUInt
      (!stream)._5 = 2.toUInt
      val sound = stackalloc[raw.Sound]()
      (!sound)._1 = !stream
      (!sound)._2 = 777.toUInt

      if !RayscalNative.ValidateStructLayout(color, camera, texture, wave, sound) then
        throw new IllegalStateException("raylib ABI field layout mismatch")
