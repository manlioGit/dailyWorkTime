package ch.view

import scalatags._
import scalatags.Text.all._
import scalatags.Text.tags2
import scala.xml.Elem
import scala.xml.XML
import ch.view.Header._

class UserView(title: String) {
   
 
  
  def render()={
    new Layout(title, scripts("login.js"), links()).render(
    
        div(cls:="container")(
            div(cls:="row")(
                div(cls:="col-md-4 col-md-offset-4")(
                    div(cls:="login-panel panel panel-default")(
                         div(cls:="panel-heading")(
                             h3(cls:="panel-title")("Login")
                         ),
                     div(cls:="panel-body")(
                         form(role:="form", action:="", method:="post")(
                            fieldset(
                                 div(cls:="form-group")(
                                     input(cls:="form-control", placeholder:="Username", name:="username", tpe:="username")
                                 ),
                                 div(cls:="form-group")(
                                     input(cls:="form-control", placeholder:="Password", name:="password", tpe:="password", value:="")
                                 ),
                                 div(cls:="checkbox")(
                                     label(
                                         input(name:="remember", tpe:="checkbox", value:="Remember Me")("Remember Me")
                                     )
                                 ),
                                 a(href:="", cls:="btn btn-lg btn-success btn-block")("Login")
                            )
                         )
                     )
                 )
             )
          )
       )
     )
   }
}