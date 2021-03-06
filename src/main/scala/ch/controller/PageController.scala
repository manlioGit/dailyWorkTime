package ch.controller

import scalatags.Text.all.body
import scalatags.Text.all.h1
import scalatags.Text.all.html
import scalatags.Text.all.stringFrag
import scalatags.Text.tags2
import ch.view.Calendar
import ch.auth.AuthenticationSupport
import ch.model.data.Driver.simple._
import ch.model.Users
import ch.view.Modal

class PageController(implicit session :Session) extends MainStack {

  before(){
    requireLogin
  }
  
  get("/*") {
    contentType = "text/html"
    
    "<!DOCTYPE html>" + new Calendar(user.name).build()
  }
  
  post("/modal/:kind/:switch?"){
     contentType = "text/html"
     
     val switch = params.get("switch") match {
      case Some("false") => false
      case _ => true
     } 
     
     new Modal(params("kind"), switch).build 
  }
}
