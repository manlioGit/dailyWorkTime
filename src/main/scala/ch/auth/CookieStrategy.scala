package ch.auth

import org.scalatra.ScalatraBase
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.scalatra.auth.ScentryStrategy
import ch.model.User
import ch.controller.Route
import org.scalatra.CookieOptions
import ch.model.data.Driver.simple._
import ch.model.Users
import ch.auth.Util._
import org.joda.time.DateTime

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
    val user = Users.retrieve(_.session === tokenVal)
    
    if(user.isEmpty){
      None
    }
    else {
      Some(user.head)
    }
  }
  
  override def unauthenticated()(implicit request: HttpServletRequest, response: HttpServletResponse) {
    app.redirect(Route.LOGIN)
  }
  
  override def afterAuthenticate(winningStrategy: String, user: User)
    (implicit request: HttpServletRequest,
    response: HttpServletResponse) = {
    
    if (winningStrategy == name || (winningStrategy == "Login" && checkbox2boolean(app.params.get("remember").
            getOrElse("").toString))) {
        
      
        user.session = randomFrom(s"${user.name}${DateTime.now()}" )
        
        Users.update(user)
        
        app.cookies.
          set(CookieKey, user.session)(CookieOptions( maxAge = oneMonth, path = "/"))
      }
    }
  
    override def beforeLogout(user: User)
      (implicit request: HttpServletRequest,
      response: HttpServletResponse) = {
        if(user != null){
          user.session = ""
          Users.update(user)
        }
        app.cookies.delete(CookieKey)(CookieOptions(path = "/"))
    }

    private def checkbox2boolean(s: String): Boolean = {
      List("yes", "y", "1", "true").contains(s) 
    }
}