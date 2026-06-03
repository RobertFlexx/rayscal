package rayscal

import rayscal.raw.Raylib

object Time:
  def frameTime: Float =
    Raylib.GetFrameTime()

  def elapsed: Double =
    Raylib.GetTime()

  def fps: Int =
    Raylib.GetFPS()
