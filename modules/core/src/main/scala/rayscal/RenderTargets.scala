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

  def texture(target: RenderTexture2D): TextureView =
    target.requireLive()
    new TextureView(target, false)

  def depth(target: RenderTexture2D): TextureView =
    target.requireLive()
    new TextureView(target, true)

  def id(target: RenderTexture2D): Int =
    target.requireLive()
    (!target.ptr)._1.toInt

  def textureId(target: RenderTexture2D): Int =
    target.requireLive()
    (!target.ptr)._2.toInt

  def depthId(target: RenderTexture2D): Int =
    target.requireLive()
    (!target.ptr)._7.toInt
