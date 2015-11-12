package ch.model

import ch.model.data.Driver.simple._
import ch.model.Role._
import slick.lifted.CanBeQueryCondition

case class User(name: String, var role: Role = Role.NORMAL.value, pwd: String, var session: String = "", id: Option[Int] = None)

object Users { 
  
  val table = TableQuery[Users]
  
  def create(user: User)(implicit session: Session) = {
     (table returning table.map(_.id) into ((user,id) => user.copy(id=Some(id)))) += user
  }
  
  def retrieve(f: Users => Rep[Boolean])(implicit session: Session) = {
     table.filter { f }.run 
  }
  
  def update(user: User)(implicit session: Session) = {
    table.filter( _.id === user.id.get ).update(user).run
  }

  def delete(user: User)(implicit session: Session) = {
    table.filter( _.id === user.id.get ).delete.run
  }
}

class Users(tag: Tag) extends Table[User](tag, "USERS") {
  
  implicit val roleMapper = MappedColumnType.base[Role, String] (
      r => r.toString,
      r => Role.withName(r)
  )
  
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def role = column[Role]("ROLE")
  def pwd = column[String]("PWD")
  def session = column[String]("SESSION")
  
  def * = (name, role, pwd, session,  id.?) <> (User.tupled, User.unapply)
}

