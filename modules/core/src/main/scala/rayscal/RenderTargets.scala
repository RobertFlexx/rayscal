package rayscal

import rayscal.raw.{RayscalNative, RenderTexture2D as RawRenderTexture2D}
import scala.scalanative.libc.stdlib
import scala.scalanative.unsafe.*

object RenderTargets:
  def load(width: Int, height: Int): RenderTexture2D =
    val ptr = stdlib.malloc(sizeof[RawRenderTexture2D]).asInstanceOf[Ptr[RawRenderTexture2D]]
    if ptr == null then throw new OutOfMemoryError("Could not allocate RenderTexture2D")
    RayscalNative.LoadRenderTexture(ptr, width, height)
    new RenderTexture2D(ptr)

  def isValid(target: RenderTexture2D): Boolean =
    target.requireLive()
    RayscalNative.IsRenderTextureValid(target.ptr)

  def unload(target: RenderTexture2D): Unit =
    if !target.disposed then
      RayscalNative.UnloadRenderTexture(target.ptr)
      stdlib.free(target.ptr.asInstanceOf[Ptr[Byte]])
      target.disposed = true

  def withRenderTexture(width: Int, height: Int)(body: RenderTexture2D => Unit): Unit =
    val target = load(width, height)
    try body(target)
    finally unload(target)

  def texture(target: RenderTexture2D)(using zone: Zone): Texture2D =
    target.requireLive()
    val _ = zone
    val rawTarget = !target.ptr
    val texture = stackalloc[Texture2D]()
    (!texture)._1 = rawTarget._2
    (!texture)._2 = rawTarget._3
    (!texture)._3 = rawTarget._4
    (!texture)._4 = rawTarget._5
    (!texture)._5 = rawTarget._6
    !texture

  def depth(target: RenderTexture2D)(using zone: Zone): Texture2D =
    target.requireLive()
    val _ = zone
    val rawTarget = !target.ptr
    val texture = stackalloc[Texture2D]()
    (!texture)._1 = rawTarget._7
    (!texture)._2 = rawTarget._8
    (!texture)._3 = rawTarget._9
    (!texture)._4 = rawTarget._10
    (!texture)._5 = rawTarget._11
    !texture

  def id(target: RenderTexture2D): Int =
    target.requireLive()
    (!target.ptr)._1.toInt

  def textureId(target: RenderTexture2D): Int =
    target.requireLive()
    (!target.ptr)._2.toInt

  def depthId(target: RenderTexture2D): Int =
    target.requireLive()
    (!target.ptr)._7.toInt
