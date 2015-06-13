package ch.model

import com.github.aselab.activerecord._
import ch.model.Role._

case class User(var name: String) extends ActiveRecord {
  lazy val events = hasMany[Event]
  lazy val role = hasOne[Role]
}
object User extends ActiveRecordCompanion[User]