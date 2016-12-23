name := "Life"
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

lazy val core = (project in file("life-core")).
  enablePlugins(Common)

lazy val ui = (project in file("life-ui")).
  enablePlugins(Common, PlayScala).
  dependsOn(core)

lazy val root = (project in file(".")).
  enablePlugins(Common).
  aggregate(core, ui).
  dependsOn(core, ui)

fork in ThisBuild := true
