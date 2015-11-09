package ch.controller

import org.scalatra._
import ch.auth.AuthenticationSupport
import ch.model.data.Driver.simple._

abstract class MainStack(implicit session :Session) extends ScalatraServlet with AuthenticationSupport {

  connection = session
  
  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
//    findTemplate(requestPath) map { path =>
//      contentType = "text/html"
//      layoutTemplate(path)
//    } orElse serveStaticResource() getOrElse resourceNotFound()
    resourceNotFound()
  }
}
