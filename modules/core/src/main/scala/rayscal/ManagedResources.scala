package rayscal

import scala.scalanative.unsafe.*

final class Image private[rayscal] (private[rayscal] val ptr: Ptr[raw.Image]) extends AutoCloseable:
  private[rayscal] var disposed = false
  private[rayscal] def requireLive(): Unit =
    if disposed then throw new IllegalStateException("Image has already been unloaded")
  override def close(): Unit = Images.unload(this)

final class Texture2D private[rayscal] (private[rayscal] val ptr: Ptr[raw.Texture2D]) extends AutoCloseable:
  private[rayscal] var disposed = false
  private[rayscal] def requireLive(): Unit =
    if disposed then throw new IllegalStateException("Texture2D has already been unloaded")
  override def close(): Unit = Textures.unload(this)

final class TextureCubemap private[rayscal] (private[rayscal] val ptr: Ptr[raw.TextureCubemap]) extends AutoCloseable:
  private[rayscal] var disposed = false
  private[rayscal] def requireLive(): Unit =
    if disposed then throw new IllegalStateException("TextureCubemap has already been unloaded")
  override def close(): Unit = Cubemaps.unload(this)

final class Shader private[rayscal] (private[rayscal] val ptr: Ptr[raw.Shader]) extends AutoCloseable:
  private[rayscal] var disposed = false
  private[rayscal] def requireLive(): Unit =
    if disposed then throw new IllegalStateException("Shader has already been unloaded")
  override def close(): Unit = Shaders.unload(this)

final class Wave private[rayscal] (private[rayscal] val ptr: Ptr[raw.Wave]) extends AutoCloseable:
  private[rayscal] var disposed = false
  private[rayscal] def requireLive(): Unit =
    if disposed then throw new IllegalStateException("Wave has already been unloaded")
  override def close(): Unit = Waves.unload(this)

final class Sound private[rayscal] (private[rayscal] val ptr: Ptr[raw.Sound], private[rayscal] val alias: Boolean) extends AutoCloseable:
  private[rayscal] var disposed = false
  private[rayscal] def requireLive(): Unit =
    if disposed then throw new IllegalStateException("Sound has already been unloaded")
  override def close(): Unit = if alias then Sounds.unloadAlias(this) else Sounds.unload(this)

final class Music private[rayscal] (private[rayscal] val ptr: Ptr[raw.Music]) extends AutoCloseable:
  private[rayscal] var disposed = false
  private[rayscal] def requireLive(): Unit =
    if disposed then throw new IllegalStateException("Music has already been unloaded")
  override def close(): Unit = MusicStreams.unload(this)
