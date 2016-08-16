import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

/**
  * Settings that are comment to all the SBT projects
  */
object Common extends AutoPlugin {
  override def trigger = allRequirements
  override def requires: sbt.Plugins = JvmPlugin

  override def projectSettings = Seq(
    organization := "com.wizzard",
    version := "0.1-SNAPSHOT",
    resolvers += Resolver.typesafeIvyRepo("releases"),
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    scalacOptions ++= Seq(
      "-encoding", "UTF-8",
      "-target:jvm-1.8",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xlint",
      "-Yno-adapted-args",
      "-Ywarn-numeric-widen"
    ),
    scalaVersion := "2.11.8",
    scalacOptions in Test ++= Seq("-Yrangepos"),
    autoAPIMappings := true,
    libraryDependencies ++= Seq(
      "javax.inject" % "javax.inject" % "1",
      "org.slf4j" % "slf4j-api" % "1.7.21"
    )
  )
}
