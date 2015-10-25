package ch.auth

import org.scalatra.auth.{ScentryConfig, ScentrySupport}
import org.scalatra.auth.strategy.BasicAuthSupport
import org.scalatra.ScalatraBase
import ch.model.User
import slick.lifted.TableQuery
import ch.controller.Route


trait AuthenticationSupport extends ScalatraBase
  with ScentrySupport[User] {
    self: ScalatraBase =>
    protected val scentryConfig = (new ScentryConfig {}).asInstanceOf[ScentryConfiguration]

    protected def fromSession = { case id: String => null }
    protected def toSession   = { case usr: User => usr.id.toString }

    protected def requireLogin() = {
      if(!isAuthenticated) {
        redirect(Route.LOGIN)
      }
    }
    
    override protected def configureScentry = {
      scentry.unauthenticated {
        scentry.strategies("UserPassword").unauthenticated()
      }
    }
    
    override protected def registerAuthStrategies = {
      scentry.register("UserPassword", app => new LoginStrategy(app))
    }
}