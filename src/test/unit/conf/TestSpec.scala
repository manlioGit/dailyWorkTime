package conf

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.OptionValues
import org.scalatest.Inside
import org.scalatest.Inspectors
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfterEach

abstract class TestSpec extends FunSuite with Matchers with
  OptionValues with Inside with Inspectors with BeforeAndAfterEach 