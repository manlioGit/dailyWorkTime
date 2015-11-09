package ch.auth

import org.scalatra.ScalatraBase
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.scalatra.auth.ScentryStrategy
import ch.model.User
import ch.controller.Route
import org.scalatra.CookieOptions
import ch.model.data.Driver.simple._
import ch.model.Users

class CookieStrategy(protected val app: ScalatraBase)
    (implicit request: HttpServletRequest,
    response: HttpServletResponse, session: Session)
    extends ScentryStrategy[User] {
  
  override def name: String = "Cookie"
  
  val CookieKey = "rememberMe"
  private val oneMonth = 30 * 7 * 24 * 3600
  
  private def tokenVal = {
    app.cookies.get(CookieKey) match {
      case Some(token) => token
      case None => ""
    }
  }
  
  def authenticate()(implicit request: HttpServletRequest,response: HttpServletResponse) = {
    if(tokenVal == "manlio") 
      Some(Users.where(_.name === "manlio").head)
    else None
  }
  
  override def unauthenticated()(implicit request: HttpServletRequest, response: HttpServletResponse) {
    app.redirect(Route.LOGIN)
  }
  
  override def afterAuthenticate(winningStrategy: String, user: User)
    (implicit request: HttpServletRequest,
    response: HttpServletResponse) = {
    
    if (winningStrategy == name || (winningStrategy == "Login" && checkbox2boolean(app.params.get("remember").
            getOrElse("").toString))) {
        
        app.cookies.
        set(CookieKey, user.name)(CookieOptions( maxAge = oneMonth, path = "/"))
      }
    }
  
    /**
    * Used to easily match a checkbox value
    */
    private def checkbox2boolean(s: String): Boolean = {
      List("yes", "y", "1", "true").contains(s) 
    }
}