package ch.controller

import ch.TestSupport
import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike
import ch.model.data.Driver.simple._
import ch.model._
import ch.controller.Route._
 
class UserControllerTest extends TestSupport with ScalatraSuite {
  
  addServlet(new UserController()(session), "/user/*")
  addServlet(new PageController()(session), "/pages/*")
  
	override def beforeEach(){
    (Users.table.schema ++ Events.table.schema).create	  
  }
  
  override def afterEach(){
    (Users.table.schema ++ Events.table.schema).drop   
  }
  
  test("user can register") {
    get(Route(USER,REGISTER)) {
      status should equal (200)
      body should include ("""<h3 class="panel-title">Register</h3>""")
    }
  }
  
  test("user not registered can access only login page") {
    get(Route(CALENDAR)){
       status should equal (302)
       header("Location") should include(Route(USER, LOGIN))
    }
  }
   
  test("user can login after registration") {
    session{
      post(Route(USER,REGISTER), "username" -> "name", "password" -> "pwd") {
        status should equal (302)
        header("Location") should include(Route(USER, LOGIN))
        
      }
      post(Route(USER,LOGIN), "username" -> "name", "password" -> "pwd") {
        status should equal (302)
        header("Location") should include(Route(CALENDAR))
      }
      get(Route(CALENDAR)){
        body should include ("""name""")
        body should include ("""div id="calendar"""")
      }
    }
  }
  
  test("user can logout") {
    get(Route(USER, LOGOUT)){
       status should equal (302)
       header("Location") should include(Route(USER,LOGIN))
    }
  }
}