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
        ("title" -> "Pasqua") ~
        ("start" -> "2016-03-27") ~
        ("end" -> "2016-03-27") ~
        ("action" -> "background"),
        ("title" -> "Pasquetta") ~
        ("start" -> "2016-03-28") ~
        ("end" -> "2016-03-28") ~
        ("action" -> "background")
      )
    ) 
    holidayJson
  }
  
  post("/create"){
    "yay!"
    
  }
}