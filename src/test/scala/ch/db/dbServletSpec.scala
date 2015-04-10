package ch.db

import org.scalatra.test.specs2._
import ch.controller.dbServlet

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class dbServletSpec extends ScalatraSpec { def is =
  "GET / on dbServlet"                     ^
    "should return status 200"                  ! root200^
                                                end

  addServlet(classOf[dbServlet], "/*")

  def root200 = get("/") {
    status must_== 200
  }
}
