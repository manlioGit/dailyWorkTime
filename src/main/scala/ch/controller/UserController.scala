package ch.controller

import ch.auth.AuthenticationSupport
import ch.view.UserView

class UserController extends MainStack /*with AuthenticationSupport*/ {
  
  
  
  post("/login") {
//    scentry.authenticate()
//    if (isAuthenticated) {
//      redirect("/pages")
//    } else {
//      redirect(Route.LOGIN)
//    }
  }
  
  get("/login") {
    contentType = "text/html"
    new UserView("login").render()
  }
  
  get("/new") {
    contentType = "text/html"
    new UserView("register").render()
  }
}