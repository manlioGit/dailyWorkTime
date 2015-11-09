package ch.auth

import java.math.BigInteger
import java.security.MessageDigest

object Util {
  
  def hashOf(s:String) = {
    new BigInteger(1,MessageDigest.getInstance("SHA-256").digest(s.getBytes)).toString(16)
  }
}