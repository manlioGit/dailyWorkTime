package ch.model

import com.github.aselab.activerecord._
import org.joda.time.DateTime

case class Event(var title: String, var start: DateTime, var end: DateTime,
                 var url: Option[String]) extends ActiveRecord{
  lazy val user = belongsTo[User]
}

object Event extends ActiveRecordCompanion[Event]