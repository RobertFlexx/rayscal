package rayscal

import rayscal.raw.{Raylib, RayscalNative}
import scala.scalanative.libc.stdlib
import scala.scalanative.unsafe.*

object Audio:
  def withDevice(body: => Unit): Unit =
    RaylibAbi.validate()
    Raylib.InitAudioDevice()
    try body
    finally Raylib.CloseAudioDevice()

  def isReady: Boolean =
    Raylib.IsAudioDeviceReady()

  def setMasterVolume(volume: Float): Unit =
    Raylib.SetMasterVolume(volume)

  def masterVolume: Float =
    Raylib.GetMasterVolume()

object Waves:
  def load(fileName: String): Wave =
    Zone:
      val wave = allocate()
      RayscalNative.LoadWave(wave.ptr, toCString(fileName))
      wave

  def isValid(wave: Wave): Boolean =
    wave.requireLive()
    RayscalNative.IsWaveValid(wave.ptr)

  def unload(wave: Wave): Unit =
    if !wave.disposed then
      if RayscalNative.IsWaveValid(wave.ptr) then RayscalNative.UnloadWave(wave.ptr)
      stdlib.free(wave.ptr.asInstanceOf[Ptr[Byte]])
      wave.disposed = true

  def withWave(fileName: String)(body: Wave => Unit): Unit =
    val wave = load(fileName)
    try body(wave)
    finally unload(wave)

  private def allocate(): Wave =
    val ptr = stdlib.malloc(sizeof[raw.Wave]).asInstanceOf[Ptr[raw.Wave]]
    if ptr == null then throw new OutOfMemoryError("Could not allocate Wave")
    new Wave(ptr)

object Sounds:
  def load(fileName: String): Sound =
    Zone:
      val sound = allocate(alias = false)
      RayscalNative.LoadSound(sound.ptr, toCString(fileName))
      sound

  def fromWave(wave: Wave): Sound =
    wave.requireLive()
    val sound = allocate(alias = false)
    RayscalNative.LoadSoundFromWave(sound.ptr, wave.ptr)
    sound

  def alias(source: Sound): Sound =
    source.requireLive()
    val sound = allocate(alias = true)
    RayscalNative.LoadSoundAlias(sound.ptr, source.ptr)
    sound

  def isValid(sound: Sound): Boolean =
    sound.requireLive()
    RayscalNative.IsSoundValid(sound.ptr)

  def unload(sound: Sound): Unit =
    if !sound.disposed then
      if RayscalNative.IsSoundValid(sound.ptr) then RayscalNative.UnloadSound(sound.ptr)
      stdlib.free(sound.ptr.asInstanceOf[Ptr[Byte]])
      sound.disposed = true

  def unloadAlias(sound: Sound): Unit =
    if !sound.disposed then
      if RayscalNative.IsSoundValid(sound.ptr) then RayscalNative.UnloadSoundAlias(sound.ptr)
      stdlib.free(sound.ptr.asInstanceOf[Ptr[Byte]])
      sound.disposed = true

  def withSound(fileName: String)(body: Sound => Unit): Unit =
    val sound = load(fileName)
    try body(sound)
    finally unload(sound)

  def play(sound: Sound): Unit =
    sound.requireLive()
    RayscalNative.PlaySound(sound.ptr)

  def stop(sound: Sound): Unit =
    sound.requireLive()
    RayscalNative.StopSound(sound.ptr)

  def pause(sound: Sound): Unit =
    sound.requireLive()
    RayscalNative.PauseSound(sound.ptr)

  def resume(sound: Sound): Unit =
    sound.requireLive()
    RayscalNative.ResumeSound(sound.ptr)

  def isPlaying(sound: Sound): Boolean =
    sound.requireLive()
    RayscalNative.IsSoundPlaying(sound.ptr)

  def setVolume(sound: Sound, volume: Float): Unit =
    sound.requireLive()
    RayscalNative.SetSoundVolume(sound.ptr, volume)

  def setPitch(sound: Sound, pitch: Float): Unit =
    sound.requireLive()
    RayscalNative.SetSoundPitch(sound.ptr, pitch)

  def setPan(sound: Sound, pan: Float): Unit =
    sound.requireLive()
    RayscalNative.SetSoundPan(sound.ptr, pan)

  private def allocate(alias: Boolean): Sound =
    val ptr = stdlib.malloc(sizeof[raw.Sound]).asInstanceOf[Ptr[raw.Sound]]
    if ptr == null then throw new OutOfMemoryError("Could not allocate Sound")
    new Sound(ptr, alias)

object MusicStreams:
  def load(fileName: String): Music =
    Zone:
      val music = allocate()
      RayscalNative.LoadMusicStream(music.ptr, toCString(fileName))
      music

  def isValid(music: Music): Boolean =
    music.requireLive()
    RayscalNative.IsMusicValid(music.ptr)

  def unload(music: Music): Unit =
    if !music.disposed then
      if RayscalNative.IsMusicValid(music.ptr) then RayscalNative.UnloadMusicStream(music.ptr)
      stdlib.free(music.ptr.asInstanceOf[Ptr[Byte]])
      music.disposed = true

  def withMusic(fileName: String)(body: Music => Unit): Unit =
    val music = load(fileName)
    try body(music)
    finally unload(music)

  def play(music: Music): Unit =
    music.requireLive()
    RayscalNative.PlayMusicStream(music.ptr)

  def update(music: Music): Unit =
    music.requireLive()
    RayscalNative.UpdateMusicStream(music.ptr)

  def stop(music: Music): Unit =
    music.requireLive()
    RayscalNative.StopMusicStream(music.ptr)

  def pause(music: Music): Unit =
    music.requireLive()
    RayscalNative.PauseMusicStream(music.ptr)

  def resume(music: Music): Unit =
    music.requireLive()
    RayscalNative.ResumeMusicStream(music.ptr)

  def isPlaying(music: Music): Boolean =
    music.requireLive()
    RayscalNative.IsMusicStreamPlaying(music.ptr)

  def seek(music: Music, position: Float): Unit =
    music.requireLive()
    RayscalNative.SeekMusicStream(music.ptr, position)

  def setVolume(music: Music, volume: Float): Unit =
    music.requireLive()
    RayscalNative.SetMusicVolume(music.ptr, volume)

  def setPitch(music: Music, pitch: Float): Unit =
    music.requireLive()
    RayscalNative.SetMusicPitch(music.ptr, pitch)

  def setPan(music: Music, pan: Float): Unit =
    music.requireLive()
    RayscalNative.SetMusicPan(music.ptr, pan)

  def length(music: Music): Float =
    music.requireLive()
    RayscalNative.GetMusicTimeLength(music.ptr)

  def played(music: Music): Float =
    music.requireLive()
    RayscalNative.GetMusicTimePlayed(music.ptr)

  private def allocate(): Music =
    val ptr = stdlib.malloc(sizeof[raw.Music]).asInstanceOf[Ptr[raw.Music]]
    if ptr == null then throw new OutOfMemoryError("Could not allocate Music")
    new Music(ptr)
