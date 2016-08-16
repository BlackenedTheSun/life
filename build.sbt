name := "Life"

val commonSettings = Seq(
  scalaVersion := "2.11.8",
  version := "1.0"
)

lazy val core = (project in file("life-core"))
  .settings(commonSettings: _*)

lazy val ui = (project in file("life-ui"))
  .settings(commonSettings: _*)
  .dependsOn(core)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .aggregate(core, ui)
