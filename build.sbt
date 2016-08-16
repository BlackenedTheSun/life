name := "Life"

version := "1.0"

scalaVersion := "2.11.8"

lazy val core = (project in file("life-core"))

lazy val ui = (project in file("life-ui"))
  .dependsOn(core)

lazy val root = (project in file("."))
  .aggregate(core, ui)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.2" % "test"
