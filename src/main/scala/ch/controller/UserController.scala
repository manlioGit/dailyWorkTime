package ch.controller

import ch.auth.AuthenticationSupport
import ch.view.UserView
import ch.model.User
import ch.model.Role
import ch.model.data.Driver.simple._
import ch.model.Users
import ch.auth.Util._

class UserController(implicit session:Session) extends MainStack {
  
	before() {
    contentType = "text/html"
  }
  
  
  post(s"/${Keys.LOGIN}") {
    scentry.authenticate()
    if (isAuthenticated) {
      redirect("/pages")
    } else {
      redirect(Route.LOGIN)
    }
  }
  
  get(s"/${Keys.LOGIN}") {
    new UserView(Keys.LOGIN, List("Username", "Password"), Map( "success" -> Keys.LOGIN, "primary" -> Keys.REGISTER)).render()
  }
  
   get(s"/${Keys.LOGOUT}") {
    scentry.logout()
    redirect(Route.LOGIN)
  }
  
  get(s"/${Keys.REGISTER}") {
    new UserView(Keys.REGISTER, List("Username", "Password", "Password (re-type)"), Map( "success" -> Keys.REGISTER)).render()
  }
  
  post(s"/${Keys.REGISTER}") {

    val user = User(params("username"), Role.NORMAL, hashOf(params("password")));

    redirect(s"/pages/index.html")
  }
}