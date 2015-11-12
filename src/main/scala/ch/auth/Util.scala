package ch.auth

import java.math.BigInteger
import java.security.MessageDigest
import scala.util.Random
import java.security.SecureRandom

object Util {
  
  def hashOf(s: String) = {
    new BigInteger(1,MessageDigest.getInstance("SHA-256").digest(s.getBytes)).toString(16)
  }
  
  def randomFrom(s: String) = {
    val random = new Random(new SecureRandom())
    
    Stream.continually(random.nextInt(s.size)).map(s).take(20).mkString
  }
}