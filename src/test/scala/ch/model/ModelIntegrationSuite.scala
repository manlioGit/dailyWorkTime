package ch.model
import org.scalatest.FunSuite
import conf.TestSpec
import org.specs2.specification.BeforeEach

class ModelIntegrationSuite extends TestSpec {

  override def beforeEach(){
	  Tables.initialize
  }
 
  override def afterEach(){
	  Tables.cleanup
  }
  
  test("interact with db"){
    val user = User("xxx").create

    user.save
    
    val fromDb = User.findBy("name" -> "xxx")
    
    assert(fromDb.get.name == "xxx")
  }
}