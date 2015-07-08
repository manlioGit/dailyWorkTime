package ch.auth

import org.scalatra.ScalatraBase
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.scalatra.auth.ScentryStrategy
import ch.model.User
import ch.model.User


class LoginStrategy (protected val app: ScalatraBase)
                    (implicit request: HttpServletRequest, response: HttpServletResponse) 
                    extends ScentryStrategy[User] {
  
  private def login = app.params.getOrElse("login", "")
  private def password = app.params.getOrElse("password", "")
  
  def authenticate()
    (implicit request: HttpServletRequest, response: HttpServletResponse): Option[User] = {
      if(login == "foo" && password == "foo") {
        None
        //Some(User)
      } else {
        None
      }
  }
  
  override def unauthenticated() 
    (implicit request: HttpServletRequest, response: HttpServletResponse) {
    app.redirect("/user/new")
  }
}