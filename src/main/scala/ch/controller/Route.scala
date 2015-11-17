package ch.controller


object Route {
  def apply(location: String*) = location.mkString("/","/","")

  val USER = "user"
  val LOGIN = "login"
  val LOGOUT = "logout"
  val REGISTER = "register"
  val CALENDAR = "pages"
  val EVENT = "events"
  val CREATE = "create"
}
