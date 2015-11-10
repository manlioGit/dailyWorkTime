package ch.model
import org.scalatest._
import ch.TestSupport
import org.joda.time.DateTime
import ch.model.data.Driver.simple._
import org.scalatra.test.scalatest.ScalatraSuite
 
class ModelIntegrationSuite extends TestSupport {

  override def beforeEach(){
    (Users.table.schema ++ Events.table.schema).create	  
  }
    
  override def afterEach(){
    (Users.table.schema ++ Events.table.schema).drop   
  }
  
  test("insert and retrieve users"){
    Users.table ++= List(
        User("other", Role.ADMIN, ""), 
        User("name", Role.NORMAL, "")
      )

    val fromDb = Users.table.sortBy(x => x.id).run
    
    assert(fromDb.head.role === Role.ADMIN)
    assert(Users.table.size.run === 2)
  }
  
  test("user has some events"){
    Users.table += User("name", Role.NORMAL, "")
    
    val user = Users.where( u => u.name === "name").head
    
    Events.table ++= List(
    		Event("summerFestival", DateTime.now, DateTime.now, Some("http://"), user.id.get),
        Event("summerParty", DateTime.now, DateTime.now, None, user.id.get)
      )
      
    val eventFromDB = Events.where(e => e.title === "summerParty").head
    
    assert(eventFromDB.userId === user.id.get)
  }
  
  test("find by id"){
    val user = Users.create(User("name", Role.NORMAL, ""))
    
    val fromDb = Users.where( _.id === user.id.get ).head
    
    assert(user === fromDb)
  }
}