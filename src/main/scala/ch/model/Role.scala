package ch.model

import com.github.aselab.activerecord._

case class Role(name: String) extends ActiveRecord {
  lazy val user = belongsTo[User]
}

object Role extends ActiveRecordCompanion[Role]