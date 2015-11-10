package ch.controller

import ch.TestSupport
import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike
import ch.model.data.Driver.simple._
import ch.model._

class UserControllerTest extends TestSupport with ScalatraSuite {
  
  addServlet(new UserController()(session), "/user/*")
//  addServlet(new PageController()(session), "/pages/*")
  
	override def beforeEach(){
    (Users.table.schema ++ Events.table.schema).create	  
  }
    
  override def afterEach(){
    (Users.table.schema ++ Events.table.schema).drop   
  }
  
  test("user can register") {
    get(s"/user/${Keys.REGISTER}") {
      status should equal (200)
      body should include ("""<h3 class="panel-title">Register</h3>""")
    }
  }
  
  test("user can login after registration") {
    post(s"/user/${Keys.REGISTER}", "username" -> "name", "password" -> "pwd") {
      status should equal (302)
    }
    post(s"/user/${Keys.LOGIN}", "username" -> "name", "password" -> "pwd") {
      status should equal (302)
    }
//    get("/pages"){
//      body should include ("""name""")
//      body should include ("""div id="calendar"""")
//    }
  }
}