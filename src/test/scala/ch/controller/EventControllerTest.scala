package ch.controller

import ch.TestSupport
import org.scalatra.test.scalatest.ScalatraSuite
import ch.controller.Route._

class EventControllerTest extends TestSupport with ScalatraSuite {
  
  addServlet(new UserController()(session), "/events/*")
  addServlet(new UserController()(session), "/user/*")
  
//  test("logged user can create event"){
//    session{
//        post(Route(USER,REGISTER), "username" -> "name", "password" -> "pwd") {
//        }
//        val header = Map("Accept" -> "application/json", "Content-Type" -> "application/json")
//        val body = """{"name": "hello world"}"""
//        post(Route(EVENT,CREATE), headers = header, body = body) {
//          status should equal (200)
//          body should include ("""name""")
//        }
//      }
//   }
}