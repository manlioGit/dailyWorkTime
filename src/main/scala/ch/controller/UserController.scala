package ch.controller

import ch.auth.AuthenticationSupport
import ch.view.Login

class UserController extends MainStack with AuthenticationSupport {
  
  before(){
    requireLogin
  }
  
  post("/") {
    scentry.authenticate()
    if (isAuthenticated) {
      redirect("/hackers")
    } else {
      redirect("/sessions/new")
    }
  }
  
  get("/*") {
    contentType = "text/html"
    
    "<!DOCTYPE html>" + new Login().build()
  }
}