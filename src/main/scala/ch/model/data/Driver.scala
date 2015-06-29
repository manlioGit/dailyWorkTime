package ch.model.data

import com.typesafe.config.ConfigFactory
import scala.slick.driver.{H2Driver, JdbcDriver, MySQLDriver}
//http://stackoverflow.com/questions/13661339/how-to-write-database-agnostic-play-application-and-perform-first-time-database/19061993#19061993
object Driver {
  val simple = profile.simple
  lazy val profile: JdbcDriver = {
    sys.env.get("DB_ENVIRONMENT") match {
      case Some(e) => ConfigFactory.load().getString(s"$e.slickDriver") match {
        case "scala.slick.driver.H2Driver" => H2Driver
        case "scala.slick.driver.MySQLDriver" => MySQLDriver
      }
      case _ => H2Driver
    }
  }
}
