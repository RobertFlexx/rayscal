package rayscal

import rayscal.raw.Raylib
import scala.scalanative.unsafe.*

object Audio:
  def withDevice(body: => Unit): Unit =
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
      Raylib.LoadWave(toCString(fileName))

  def isValid(wave: Wave): Boolean =
    Raylib.IsWaveValid(wave)

  def unload(wave: Wave): Unit =
    Raylib.UnloadWave(wave)

  def withWave(fileName: String)(body: Wave => Unit): Unit =
    val wave = load(fileName)
    try body(wave)
    finally unload(wave)

object Sounds:
  def load(fileName: String): Sound =
    Zone:
      Raylib.LoadSound(toCString(fileName))

  def fromWave(wave: Wave): Sound =
    Raylib.LoadSoundFromWave(wave)

  def alias(source: Sound): Sound =
    Raylib.LoadSoundAlias(source)

  def isValid(sound: Sound): Boolean =
    Raylib.IsSoundValid(sound)

  def unload(sound: Sound): Unit =
    Raylib.UnloadSound(sound)

  def unloadAlias(sound: Sound): Unit =
    Raylib.UnloadSoundAlias(sound)

  def withSound(fileName: String)(body: Sound => Unit): Unit =
    val sound = load(fileName)
    try body(sound)
    finally unload(sound)

  def play(sound: Sound): Unit =
    Raylib.PlaySound(sound)

  def stop(sound: Sound): Unit =
    Raylib.StopSound(sound)

  def pause(sound: Sound): Unit =
    Raylib.PauseSound(sound)

  def resume(sound: Sound): Unit =
    Raylib.ResumeSound(sound)

  def isPlaying(sound: Sound): Boolean =
    Raylib.IsSoundPlaying(sound)

  def setVolume(sound: Sound, volume: Float): Unit =
    Raylib.SetSoundVolume(sound, volume)

  def setPitch(sound: Sound, pitch: Float): Unit =
    Raylib.SetSoundPitch(sound, pitch)

  def setPan(sound: Sound, pan: Float): Unit =
    Raylib.SetSoundPan(sound, pan)

object MusicStreams:
  def load(fileName: String): Music =
    Zone:
      Raylib.LoadMusicStream(toCString(fileName))

  def isValid(music: Music): Boolean =
    Raylib.IsMusicValid(music)

  def unload(music: Music): Unit =
    Raylib.UnloadMusicStream(music)

  def withMusic(fileName: String)(body: Music => Unit): Unit =
    val music = load(fileName)
    try body(music)
    finally unload(music)

  def play(music: Music): Unit =
    Raylib.PlayMusicStream(music)

  def update(music: Music): Unit =
    Raylib.UpdateMusicStream(music)

  def stop(music: Music): Unit =
    Raylib.StopMusicStream(music)

  def pause(music: Music): Unit =
    Raylib.PauseMusicStream(music)

  def resume(music: Music): Unit =
    Raylib.ResumeMusicStream(music)

  def isPlaying(music: Music): Boolean =
    Raylib.IsMusicStreamPlaying(music)

  def seek(music: Music, position: Float): Unit =
    Raylib.SeekMusicStream(music, position)

  def setVolume(music: Music, volume: Float): Unit =
    Raylib.SetMusicVolume(music, volume)

  def setPitch(music: Music, pitch: Float): Unit =
    Raylib.SetMusicPitch(music, pitch)

  def setPan(music: Music, pan: Float): Unit =
    Raylib.SetMusicPan(music, pan)

  def length(music: Music): Float =
    Raylib.GetMusicTimeLength(music)

  def played(music: Music): Float =
    Raylib.GetMusicTimePlayed(music)
