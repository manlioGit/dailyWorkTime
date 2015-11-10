import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._

object DbBuild extends Build {
  val Organization = "com.example"
  val Name = "dailyWorkTime"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.7"
  val ScalatraVersion = "2.3.0"

  
  lazy val project = Project (
    "dailyWorkTime",
    file("."),
      settings = ScalatraPlugin.scalatraWithJRebel ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "com.lihaoyi" %% "scalatags" % "0.5.3",
//        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.1.2" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "9.1.5.v20140505" % "compile;container",
        "org.eclipse.jetty" % "jetty-plus" % "9.1.5.v20140505" % "compile;container;provided;test",
        "javax.servlet" % "javax.servlet-api" % "3.1.0",
      	"com.typesafe.slick" %% "slick" % "3.0.0",
        "org.slf4j" % "slf4j-nop" % "1.6.4",
      	"org.xerial" % "sqlite-jdbc" % "3.8.11.2",
        "org.scalatra" %% "scalatra-json" % "2.3.0",
        "org.json4s"   %% "json4s-jackson" % "3.2.11",
        "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
        "org.scalatra" %% "scalatra-auth" % ScalatraVersion
      )
    )
  )
}
