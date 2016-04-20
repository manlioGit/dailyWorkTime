import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.earldouglas.xwp.JettyPlugin
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging

object DbBuild extends Build {
  val Organization = "com.example"
  val Name = "dailyWorkTime"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.7"
  val ScalatraVersion = "2.4.0"

//  unmanagedSourceDirectories in Compile <++= baseDirectory { base =>
//      Seq(
//        base / "src/main/webapp/js"
//  	  )
//  }

  unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil

  
  lazy val project = Project (
    "dailyWorkTime",
    file("."),
      settings = ScalatraPlugin.scalatraWithJRebel ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "com.lihaoyi" %% "scalatags" % "0.5.4",
        "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.1.2" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "9.2.15.v20160210" % "compile;container",
        "javax.servlet" % "javax.servlet-api" % "3.1.0",
      	"com.typesafe.slick" %% "slick" % "3.0.0",
        "org.slf4j" % "slf4j-nop" % "1.6.4",
      	"org.xerial" % "sqlite-jdbc" % "3.8.11.2",
      	"org.postgresql" % "postgresql" % "9.4.1208.jre7",
        "org.scalatra" %% "scalatra-json" % ScalatraVersion,
        "org.json4s"   %% "json4s-jackson" % "3.3.0",
        "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
        "org.scalatra" %% "scalatra-auth" % ScalatraVersion
      )
    )
  ).enablePlugins(JavaAppPackaging)
  .enablePlugins(JettyPlugin)
}
