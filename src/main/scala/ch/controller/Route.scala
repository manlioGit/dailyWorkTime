package ch.controller


object Route {
  val LOGIN = s"/${Keys.USER}/${Keys.LOGIN}";
  val LOGOUT = s"/${Keys.USER}/${Keys.LOGOUT}";
}

object Keys {
  val USER = "user"
  val LOGIN = "login"
  val LOGOUT = "logout"
  val REGISTER = "register"
}