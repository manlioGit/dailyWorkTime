package ch.auth

import org.scalatra.ScalatraBase
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.scalatra.auth.ScentryStrategy
import ch.model.User
import ch.controller.Route
import ch.controller.Route._
import ch.model.Users
import ch.model.data.Driver.simple._

class LoginStrategy (protected val app: ScalatraBase)
                    (implicit request: HttpServletRequest, response: HttpServletResponse, session: Session) 
                    extends ScentryStrategy[User] {
  
  override def name: String = "Login"
  
  private def username = app.params.getOrElse("username", "")
  private def password = app.params.getOrElse("password", "")
  
  def authenticate()
    (implicit request: HttpServletRequest, response: HttpServletResponse) = {
    
    val user = Users.retrieve( u => (u.name === username && u.pwd === Util.hashOf(password)))
    
    if(user.isEmpty){
      None
    }
    else {
      Some(user.head)
    }
  }
  
  override def isValid(implicit request: HttpServletRequest) = {
    username != "" && password != ""
  }
  
  override def unauthenticated() 
    (implicit request: HttpServletRequest, response: HttpServletResponse) {
    app.redirect(Route(USER, LOGIN))
  }
}