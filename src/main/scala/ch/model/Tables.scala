package ch.model

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

object Tables extends ActiveRecordTables {
  val user = table[User]
  val events = table[Event]

}