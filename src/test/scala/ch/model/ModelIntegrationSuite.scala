package ch.model
import org.scalatest._
import conf.TestSpec
import org.specs2.specification.BeforeEach
//import slick.driver.H2Driver.api._
import scala.slick.driver.H2Driver.simple._
//import slick.jdbc.meta._


class ModelIntegrationSuite extends TestSpec {

  val users = TableQuery[Users]
  val events = TableQuery[Events]
  
  implicit var session: Session = _
  
  before {
    session = Database.forURL("jdbc:h2:mem:test1", driver = "org.h2.Driver").createSession()
  }
  
  after {
    session.close()
  }
  
  override def beforeEach(){
    (users.schema ++ events.schema).create	  
  }
  
  test("interact with db"){
    users.insert(User("name",Role.NORMAL.toString()))
    users += User("other",Role.ADMIN.toString())

    val fromDb = users.filter(_.name === "other" ).run

    assert(fromDb.head.role == Role.ADMIN.toString())
    assert(users.list.size === 2)
  }
}