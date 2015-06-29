package ch.model
import org.scalatest._
import conf.TestSpec
import org.specs2.specification.BeforeEach
import org.joda.time.DateTime
import ch.model.data.Driver.simple._

class ModelIntegrationSuite extends TestSpec {

  val users = TableQuery[Users]
  val events = TableQuery[Events]
  
  implicit var session: Session = _
  
  before {
    session = Database.forConfig("h2mem1").createSession()
  }
  
  after {
    session.close()
  }
  
  override def beforeEach(){
    (users.schema ++ events.schema).create	  
  }
  
  override def afterEach(){
    (users.schema ++ events.schema).drop   
  }
 
  test("insert and retrieve users"){
    users ++= List(
        User("other",Role.ADMIN), 
        User("name",Role.NORMAL)
      )

    val fromDb = users.sortBy(x => x.id).run
    
    assert(fromDb.head.role === Role.ADMIN)
    assert(users.size.run === 2)
  }
  
  test("user has some events"){
    users += User("name", Role.NORMAL)
    
    val user = users.filter { u => u.name === "name" }.run.head
    
    events ++= List(
    		Event("summerFestival", DateTime.now, DateTime.now, Some("http://"), user.id.get),
        Event("summerParty", DateTime.now, DateTime.now, None, user.id.get)
      )
      
    val eventFromDB = events.filter(e => e.title === "summerParty").run.head
    
    assert(eventFromDB.userId === user.id.get)
  }
}