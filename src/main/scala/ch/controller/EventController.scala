package ch.controller

import org.scalatra._
import org.scalatra.json._
import org.json4s._
import org.json4s.JsonDSL._
import ch.model.Event
import ch.model.User
import ch.model.data.Driver.simple._

class EventController(implicit session: Session) extends MainStack with JacksonJsonSupport {
  
  implicit val jsonFormats = DefaultFormats

  before(){
     requireLogin
  }
  
  get("/holiday") {
    val holidayJson = JArray(List(
        ("title" -> "xxx") ~
        ("start" -> "2015-05-15")
      )
    ) 
    holidayJson
  }
  
  post("/create"){

    
  }
}