package ch.model

import ch.model.data.Driver.simple._
import ch.model.Role._

case class User(name: String, role: Role, id: Option[Int] = None)

class Users(tag: Tag) extends Table[User](tag, "USERS") {
  
  implicit val roleMapper = MappedColumnType.base[Role, String] (
      r => r.toString,
      r => Role.withName(r)
  )
  
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def role = column[Role]("ROLE")
  
  def * = (name, role, id.?) <> (User.tupled, User.unapply)
}

/*
// define enumeration
object BillingFrequency extends Enumeration {
  type BillingFrequency = Value
  val MONTHLY, ANNUAL = Value
}
 
// map to database with Slick
def billingFrequency = column[BillingFrequency]("billing_frequency")
 
// map between enumeration and database
implicit val BillingFrequencyMapper = MappedColumnType.base[BillingFrequency, String](
  s => s.toString,
  s => BillingFrequency.withName(s)
)
*/
/*
 case class Event(id: Long, description: String, timestamp: java.util.Date)

class Events(tag: Tag) extends Table[Event](tag, "EVENTS") {
    implicit val javaUtilDateMapper = MappedColumnType.base[java.util.Date, java.sql.Timestamp] (
        d => new java.sql.Timestamp(d.getTime),
        d => new java.sql.Date(d.getTime)
    )
    def id = column[Long]("id", O.PrimaryKey)
    def description = column[String]("description")
    def timestamp = column[java.util.Date]("timestamp")

    def * = (id, description, timestamp) <> (Event.tupled, Event.unapply _)
}
 */
