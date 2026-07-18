package rayscal

import scala.scalanative.unsafe.*

final class Font private[rayscal] (private[rayscal] val ptr: Ptr[Byte]) extends AutoCloseable:
  private[rayscal] var disposed = false

  private[rayscal] def requireLive(): Unit =
    if disposed then throw new IllegalStateException("Font has already been unloaded")

  override def close(): Unit = Fonts.unload(this)
