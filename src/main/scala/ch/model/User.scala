package ch.model

//import slick.driver.H2Driver.simple._
import slick.driver.H2Driver.api._

case class User(id: Option[Int] = None, name: String, role: String)

class Users(tag: Tag) extends Table[User](tag, "USERS") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc) // This is the primary key column
  def name = column[String]("NAME")
  def role = column[String]("ROLE")
  
  def * = (id.?, name, role) <> (User.tupled, User.unapply)
}
