package ch.model

object Role extends Enumeration {
  type Role = Value
  val NORMAL = Value("NORMAL")
  val ADMIN = Value("ADMIN")
}