package ch.controller

import ch.auth.AuthenticationSupport
import ch.view.UserView
import ch.model.User
import ch.model.User
import ch.model.Role
import java.security.MessageDigest
import java.math.BigInteger

class UserController extends MainStack /*with AuthenticationSupport*/ {
  
  
  
  post(s"/${Keys.LOGIN}") {
//    scentry.authenticate()
//    if (isAuthenticated) {
//      redirect("/pages")
//    } else {
//      redirect(Route.LOGIN)
//    }
  }
  
  get(s"/${Keys.LOGIN}") {
    contentType = "text/html"
    new UserView(Keys.LOGIN, List("Username", "Password"), Map( "success" -> Keys.LOGIN, "primary" -> Keys.REGISTER)).render()
  }
  
  get(s"/${Keys.REGISTER}") {
    contentType = "text/html"
    new UserView(Keys.REGISTER, List("Username", "Password", "Password (re-type)"), Map( "success" -> Keys.REGISTER)).render()
  }
  
  post(s"/${Keys.REGISTER}") {
    val user = User(params("username"), Role.NORMAL, hashOf(params("password")));    

    redirect(s"/pages/index.html?user=${user.name}")
  }
  
  def hashOf(s:String) = {
    new BigInteger(1,MessageDigest.getInstance("SHA-256").digest(s.getBytes)).toString(16)
  }
}