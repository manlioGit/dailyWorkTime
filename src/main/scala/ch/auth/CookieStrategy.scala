package ch.auth

import org.scalatra.ScalatraBase
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.scalatra.auth.ScentryStrategy
import ch.model.User
import ch.controller.Route
import ch.controller.Route._
import org.scalatra.CookieOptions
import ch.model.data.Driver.simple._
import ch.model.Users

class CookieStrategy(protected val app: ScalatraBase)
    (implicit request: HttpServletRequest,
    response: HttpServletResponse, session: Session)
    extends ScentryStrategy[User] {
  
  override def name: String = "Cookie"
  
  private def tokenVal = {
    app.cookies.get(Strategy.KEY) match {
      case Some(token) => token
      case None => "NO_COOKIE"
    }
  }
  
  override def isValid(implicit request: HttpServletRequest):Boolean = {
    tokenVal != "NO_COOKIE"
  }
  
  def authenticate()(implicit request: HttpServletRequest,response: HttpServletResponse) = {
    val user = Users.retrieve(_.session === tokenVal)
    
    if(user.isEmpty){
      None
    }
    else {
      Some(user.head)
    }
  }
  
  override def unauthenticated()(implicit request: HttpServletRequest, response: HttpServletResponse) {
    app.redirect(Route(USER, LOGIN))
  }
  
  override def beforeLogout(user: User)
      (implicit request: HttpServletRequest,
      response: HttpServletResponse) = {
        if(user != null){
          user.session = ""
          Users.update(user)
        }
        app.cookies.delete(Strategy.KEY)(CookieOptions(path = "/"))
    }
}