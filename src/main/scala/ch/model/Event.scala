package ch.model

import ch.model.data.Driver.simple._
import java.sql.Date
import org.joda.time.DateTime

case class Event(title: String, start: DateTime, end:DateTime, url: Option[String],userId: Int, id: Option[Int] = None)

class Events(tag: Tag) extends Table[Event](tag, "EVENTS") {
  
  implicit val dateMapper = MappedColumnType.base[DateTime, Date] (
    dateTime => new Date(dateTime.getMillis),
    date => new DateTime(date)
  )
  
  def title = column[String]("TITLE")
  def start = column[DateTime]("START")
  def end = column[DateTime]("END")
  def url = column[Option[String]]("URL")
  def userId = column[Int]("USER_ID")
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  
  def user = foreignKey("SUP_FK", userId, TableQuery[Users])(_.id)
  
  def * = (title, start, end, url, userId, id.?) <> (Event.tupled, Event.unapply)
}
