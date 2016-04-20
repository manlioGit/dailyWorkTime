package ch.model

import ch.model.data.Driver.simple._
import java.sql.Date
import org.joda.time.DateTime

case class Event(title: String, start: DateTime, end:DateTime, url: Option[String],userId: Int, id: Option[Int] = None)

object Events {
   val table = TableQuery[Events]
   
    def where(f: Events => Rep[Boolean])(implicit session: Session) = {
     table.filter { f }.run 
  }
}

class Events(tag: Tag) extends Table[Event](tag, "events") {
  
  implicit val dateMapper = MappedColumnType.base[DateTime, Date] (
    dateTime => new Date(dateTime.getMillis),
    date => new DateTime(date)
  )
  
  def title = column[String]("title")
  def start = column[DateTime]("start")
  def end = column[DateTime]("end")
  def url = column[Option[String]]("url")
  def userId = column[Int]("user_id")
  def id = column[Int]("id", O.PrimaryKey)
  
  def user = foreignKey("SUP_FK", userId, TableQuery[Users])(_.id)
  
  def * = (title, start, end, url, userId, id.?) <> (Event.tupled, Event.unapply)
}
