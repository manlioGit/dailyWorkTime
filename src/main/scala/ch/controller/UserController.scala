package ch.controller

import ch.auth.AuthenticationSupport
import ch.view.UserView
import ch.model.User
import ch.model.Role
import ch.model.data.Driver.simple._
import ch.model.Users
import ch.auth.Util._
import ch.controller.Route._

class UserController(implicit session:Session) extends MainStack {
  
	before() {
    contentType = "text/html"
  }
 
  post(Route(LOGIN)) {
    scentry.authenticate()
    if (isAuthenticated) {
      redirect(Route(CALENDAR))
    } else {
      redirect(Route(USER, LOGIN))
    }
  }
  
  get(Route(LOGIN)) {
    new UserView(LOGIN, List("Username", "Password"), Map( "success" -> LOGIN, "primary" -> REGISTER)).render()
  }
  
  get(Route(LOGOUT)) {
    scentry.logout()
    redirect(Route(USER, LOGIN))
  }
  
  get(Route(REGISTER)) {
    new UserView(REGISTER, List("Username", "Password", "Password (re-type)"), Map( "success" -> REGISTER)).render()
  }
  
  post(Route(REGISTER)) {
    
    Users.create(User(params("username"), Role.NORMAL, hashOf(params("password"))))
    
    redirect(Route(USER,LOGIN))
  }
}