package ch.auth

import org.scalatra.ScalatraBase
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.scalatra.auth.ScentryStrategy
import ch.model.User
import ch.controller.Route
import ch.controller.Route._
import ch.model.Users
import ch.model.data.Driver.simple._
import org.slf4j.LoggerFactory
import org.scalatra.CookieOptions
import ch.auth.Util._
import org.joda.time.DateTime

class LoginStrategy (protected val app: ScalatraBase)
                    (implicit request: HttpServletRequest, response: HttpServletResponse, session: Session) 
                    extends ScentryStrategy[User] {
  
  override def name: String = "Login"
  
  private def username = app.params.getOrElse("username", "")
  private def password = app.params.getOrElse("password", "")
  private def remember = app.params.getOrElse("remember", "")
  
  private val oneMonth = 4 * 7 * 24 * 3600
  
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
  
  override def afterAuthenticate(winningStrategy: String, user: User)
    (implicit request: HttpServletRequest,
    response: HttpServletResponse) = {
    
    if (winningStrategy == "Login" && checkbox2boolean(remember)) {
      
        user.session = randomFrom(s"${user.name}${user.pwd}${DateTime.now()}" )
        
        Users.update(user)
        
        app.cookies.
          set(Strategy.KEY, user.session)(CookieOptions( maxAge = oneMonth, path = "/"))
      }
    }
  
  private def checkbox2boolean(s: String): Boolean = {
      List("yes", "y", "1", "true", "remember").contains(s) 
  }
}