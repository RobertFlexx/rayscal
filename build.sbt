import scala.scalanative.build._
import scala.sys.process._
import scala.util.Try

def raylibLinkingOptions: Seq[String] = {
  val pkgConfigOptions = Try("pkg-config --libs raylib".!!.trim)
    .toOption
    .filter(_.nonEmpty)
    .map(_.split("\\s+").toSeq)
    .getOrElse(Seq("-lraylib"))

  val rpaths = pkgConfigOptions.collect {
    case option if option.startsWith("-L") && option.length > 2 =>
      s"-Wl,-rpath,${option.drop(2)}"
  }

  pkgConfigOptions ++ rpaths
}

def raylibCompileOptions: Seq[String] =
  Try("pkg-config --cflags raylib".!!.trim)
    .toOption
    .filter(_.nonEmpty)
    .map(_.split("\\s+").toSeq)
    .getOrElse(Seq.empty)

def hostDynamicLinkerOptions: Seq[String] = {
  val interpreterPattern = ".*Requesting program interpreter: ([^\\]]+)\\].*".r
  val fromShell = Try("readelf -l /bin/sh".!!.linesIterator.collectFirst {
    case interpreterPattern(path) => path.trim
  }).toOption.flatten

  val fallback = Seq(
    "/usr/x86_64-pc-linux-gnu/lib/ld-linux-x86-64.so.2",
    "/lib64/ld-linux-x86-64.so.2",
    "/lib/ld-linux-x86-64.so.2"
  ).find(file(_).exists)

  (fromShell.orElse(fallback)).toSeq.map(path => s"-Wl,--dynamic-linker=$path")
}

ThisBuild / organization := "io.github.rayscal"
ThisBuild / scalaVersion := "3.7.3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT"))

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Wunused:all"
  ),
  nativeConfig ~= { config =>
    config
      .withLinkingOptions(config.linkingOptions ++ raylibLinkingOptions ++ hostDynamicLinkerOptions)
      .withCompileOptions(config.compileOptions ++ raylibCompileOptions)
  }
)

lazy val root = project
  .in(file("."))
  .aggregate(core, helloWindow, bouncingBall, keyboardInput, rlglTriangle, shapesGallery, textureChecker, basic3d, camera2d, renderTexture)
  .settings(
    name := "rayscal-root",
    publish / skip := true
  )

lazy val core = project
  .in(file("modules/core"))
  .enablePlugins(ScalaNativePlugin)
  .settings(commonSettings)
  .settings(
    name := "rayscal-core"
  )

lazy val helloWindow = project
  .in(file("examples/hello-window"))
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "rayscal-hello-window",
    publish / skip := true
  )

lazy val bouncingBall = project
  .in(file("examples/bouncing-ball"))
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "rayscal-bouncing-ball",
    publish / skip := true
  )

lazy val keyboardInput = project
  .in(file("examples/keyboard-input"))
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "rayscal-keyboard-input",
    publish / skip := true
  )

lazy val rlglTriangle = project
  .in(file("examples/rlgl-triangle"))
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "rayscal-rlgl-triangle",
    publish / skip := true
  )

lazy val shapesGallery = project
  .in(file("examples/shapes-gallery"))
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "rayscal-shapes-gallery",
    publish / skip := true
  )

lazy val textureChecker = project
  .in(file("examples/texture-checker"))
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "rayscal-texture-checker",
    publish / skip := true
  )

lazy val basic3d = project
  .in(file("examples/basic-3d"))
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "rayscal-basic-3d",
    publish / skip := true
  )

lazy val camera2d = project
  .in(file("examples/camera-2d"))
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "rayscal-camera-2d",
    publish / skip := true
  )

lazy val renderTexture = project
  .in(file("examples/render-texture"))
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "rayscal-render-texture",
    publish / skip := true
  )
