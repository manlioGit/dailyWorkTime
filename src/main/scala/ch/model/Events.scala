package ch.model

import slick.driver.H2Driver.simple._
import slick._
import java.sql.Date

case class Event(id: Int, title: String, start: Date, end:Date, url: Option[String])

class Events(tag: Tag) extends Table[Event](tag, "EVENTS") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc) // This is the primary key column
  def title = column[String]("TITLE")
  def start = column[Date]("START")
  def end = column[Date]("END")
  def url = column[String]("URL")
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, title, start, end, url.?) <> (Event.tupled, Event.unapply)
}
