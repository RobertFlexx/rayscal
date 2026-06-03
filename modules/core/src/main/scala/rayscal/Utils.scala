package rayscal

import rayscal.raw.Raylib
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

object Random:
  def setSeed(seed: Int): Unit =
    Raylib.SetRandomSeed(seed.toUInt)

  def between(min: Int, max: Int): Int =
    Raylib.GetRandomValue(min, max)

object Screenshots:
  def take(fileName: String): Unit =
    Zone:
      Raylib.TakeScreenshot(toCString(fileName))
