package ch.model
import org.scalatest.FunSuite
import conf.TestSpec
import org.specs2.specification.BeforeEach
import slick.driver.H2Driver.api._
import slick.jdbc.meta._


class ModelIntegrationSuite extends TestSpec {

  var db: Database = _
  
  override def beforeEach(){
	  db = Database.forConfig("h2mem1")
  }
 
  override def afterEach(){
  }
  
  test("interact with db"){
    
  }
}