package ch.view

import scalatags._
import scalatags.Text.all._
import scalatags.Text.tags2

class Login {
   def build()={
  //  val tags = 
    html(lang:="en")(
        head(
            meta(charset:="utf-8"),
            meta(httpEquiv:="X-UA-Compatible",content:="IE:=edge"),
            meta(name:="viewport",content:="width=device-width, initial-scale:=1"),
            meta(name:="description", content:=""),
            meta(name:="author", content:=""),
        
            tags2.title()("GmTechnologies"),
            
            link(href:="../css/bootstrap.min.css",rel:="stylesheet"),
            link(href:="../css/sb-admin-2.css",rel:="stylesheet"),
            link(href:="../css/font-awesome.min.css",rel:="stylesheet",tpe:="text/css"),
            
            script(src:="../js/jquery.min.js"),
            script(src:="../js/bootstrap.min.js"),
            script(src:="../js/metisMenu.min.js"),
            script(src:="../js/sb-admin-2.js")
        ),
        body(
            div(cls:="container")(
                div(cls:="row")(
                    div(cls:="col-md-4 col-md-offset-4")(
                        div(cls:="login-panel panel panel-default")(
                             div(cls:="panel-heading")(
                                 h3(cls:="panel-title")("Please Sign In")
                             ),
                         div(cls:="panel-body")(
                             form(role:="form")(
                                fieldset(
                                     div(cls:="form-group")(
                                         input(cls:="form-control", placeholder:="E-mail", name:="email", tpe:="email")
                                     ),
                                     div(cls:="form-group")(
                                         input(cls:="form-control", placeholder:="Password", name:="password", tpe:="password", value:="")
                                     ),
                                     div(cls:="checkbox")(
                                         label(
                                             input(name:="remember", tpe:="checkbox", value:="Remember Me")("Remember Me")
                                         )
                                     ),
                                     a(href:="index.html", cls:="btn btn-lg btn-success btn-block")("Login")
                                )
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