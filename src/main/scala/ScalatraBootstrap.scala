import ch.controller._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    System.setProperty("run.mode", "dev")
    context.mount(new MainController, "/pages/*")
    context.mount(new EventController, "/events/*")
    context.mount(new UserController, "/user/*")
  }
}
