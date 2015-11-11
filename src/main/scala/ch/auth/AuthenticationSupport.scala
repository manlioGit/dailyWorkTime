package ch.auth

import org.scalatra.auth.{ScentryConfig, ScentrySupport}
import org.scalatra.auth.strategy.BasicAuthSupport
import org.scalatra.ScalatraBase
import ch.model.User
import slick.lifted.TableQuery
import ch.controller.Route
import ch.controller.Route._
import ch.model.data.Driver.simple._
import ch.model.Users
import scala.util.Try


trait AuthenticationSupport extends ScalatraBase
  with ScentrySupport[User] {
    self: ScalatraBase =>

    implicit protected var connection: Session = _
    
    protected val scentryConfig = (new ScentryConfig {}).asInstanceOf[ScentryConfiguration]

    protected def fromSession = { case id: String => Users.where( _.id === id.toInt).head }

    protected def toSession   = { case usr: User => usr.id.get.toString }

    
    protected def requireLogin() = {
      if(!isAuthenticated) {
        redirect(Route(USER,LOGIN))
      }
    }
      
    override protected def configureScentry = {
      scentry.unauthenticated { scentry.strategies("Login").unauthenticated()}
//      scentry.unauthenticated { scentry.strategies("Cookie").unauthenticated()}
    }
    
    override protected def registerAuthStrategies = {
     
      scentry.register("Login", app => new LoginStrategy(app))
//      scentry.register("Cookie", app => new CookieStrategy(app))
    }
}