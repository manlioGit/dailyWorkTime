package ch.model.data

import com.typesafe.config.ConfigFactory
import scala.slick.driver.{SQLiteDriver, JdbcDriver, MySQLDriver}
import slick.driver.PostgresDriver

object Driver {
  
  val simple = profile.simple
  
  lazy val profile: JdbcDriver = {
    configuration match {
      case "production" => PostgresDriver
      case "local" => PostgresDriver
      case "test" => SQLiteDriver
    }
  }
  
  def configuration: String = sys.env.get("DB_ENVIRONMENT").getOrElse("test")
}
