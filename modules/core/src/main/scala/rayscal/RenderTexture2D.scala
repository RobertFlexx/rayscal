package rayscal

import scala.scalanative.unsafe.*

final class RenderTexture2D private[rayscal] (private[rayscal] val ptr: Ptr[raw.RenderTexture2D]):
  private[rayscal] var disposed: Boolean = false

  private[rayscal] def requireLive(): Unit =
    if disposed then
      throw new IllegalStateException("RenderTexture2D has already been unloaded")
