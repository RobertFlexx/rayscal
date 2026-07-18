package rayscal

import scala.scalanative.unsafe.*

final class TextureView private[rayscal] (target: RenderTexture2D, depth: Boolean):
  private[rayscal] def native(using zone: Zone): Ptr[raw.Texture2D] =
    val _ = zone
    target.requireLive()
    val rawTarget = !target.ptr
    val texture = stackalloc[raw.Texture2D]()
    if depth then
      (!texture)._1 = rawTarget._7
      (!texture)._2 = rawTarget._8
      (!texture)._3 = rawTarget._9
      (!texture)._4 = rawTarget._10
      (!texture)._5 = rawTarget._11
    else
      (!texture)._1 = rawTarget._2
      (!texture)._2 = rawTarget._3
      (!texture)._3 = rawTarget._4
      (!texture)._4 = rawTarget._5
      (!texture)._5 = rawTarget._6
    texture
