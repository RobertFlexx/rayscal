package rayscal

import rayscal.raw.{Raylib, RayscalNative}
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

object Files:
  def exists(fileName: String): Boolean =
    Zone:
      Raylib.FileExists(toCString(fileName))

  def directoryExists(path: String): Boolean =
    Zone:
      Raylib.DirectoryExists(toCString(path))

  def isFile(path: String): Boolean =
    Zone:
      Raylib.IsPathFile(toCString(path))

  def length(fileName: String): Int =
    Zone:
      Raylib.GetFileLength(toCString(fileName))

  def extension(path: String): String =
    Zone:
      fromCString(Raylib.GetFileExtension(toCString(path)))

  def name(path: String): String =
    Zone:
      fromCString(Raylib.GetFileName(toCString(path)))

  def nameWithoutExtension(path: String): String =
    Zone:
      fromCString(Raylib.GetFileNameWithoutExt(toCString(path)))

  def directory(path: String): String =
    Zone:
      fromCString(Raylib.GetDirectoryPath(toCString(path)))

  def previousDirectory(path: String): String =
    Zone:
      fromCString(Raylib.GetPrevDirectoryPath(toCString(path)))

  def workingDirectory: String =
    fromCString(Raylib.GetWorkingDirectory())

  def applicationDirectory: String =
    fromCString(Raylib.GetApplicationDirectory())

  def changeDirectory(path: String): Boolean =
    Zone:
      Raylib.ChangeDirectory(toCString(path))

object DroppedFiles:
  def isDropped: Boolean =
    Raylib.IsFileDropped()

  def withDroppedFiles(body: IndexedSeq[String] => Unit): Unit =
    val dropped = RayscalNative.LoadDroppedFiles()
    if dropped == null then throw new OutOfMemoryError("Could not allocate dropped-file list")
    try
      val count = RayscalNative.DroppedFileCount(dropped).toInt
      val paths = scala.collection.immutable.Vector.tabulate(count): index =>
        val path = RayscalNative.DroppedFilePath(dropped, index.toUInt)
        if path == null then "" else fromCString(path)
      body(paths)
    finally RayscalNative.UnloadDroppedFiles(dropped)
