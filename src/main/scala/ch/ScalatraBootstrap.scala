

import ch.controller._
import org.scalatra._
import ch.model.data.Driver.simple._
import javax.servlet.ServletContext
import org.scalatra.servlet.ScalatraListener
import scala.concurrent._
import slick.jdbc.meta.MTable
import scala.concurrent.duration.Duration

class ScalatraBootstrap extends LifeCycle {
  
	implicit var session: Session = _
  
  override def init(context: ServletContext) {
   
	  val db = Database.forConfig("local")
	  
    session = db.createSession()

    context.mount(new PageController, "/pages/*")
    context.mount(new EventController, "/events/*")
    context.mount(new UserController, "/user/*")
  }
	
	override def destroy(context:ServletContext) {
     session.close()
  }
}
