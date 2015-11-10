package ch

import org.scalatest.Matchers
import org.scalatest.OptionValues
import org.scalatest.Inside
import org.scalatest.Inspectors
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfterEach
import org.scalatest.BeforeAndAfter
import ch.model.data.Driver.simple._

abstract class TestSupport extends FunSuite 
  with Matchers with OptionValues with Inside 
  with Inspectors with BeforeAndAfterEach with BeforeAndAfter { 
  
  implicit var session: Session = Database.forConfig("test").createSession()
}