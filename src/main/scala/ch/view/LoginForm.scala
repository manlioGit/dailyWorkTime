package ch.view

import scalatags.Text.all._
import scalatags.Text.tags2

class LoginForm(dataGroup:List[String], refs:Map[String, String], act:String) {
  
  def render() = {
    
    form(role:="form", action:=act, method:="post", data.toggle:="validator")(
        fieldset(
           dataGroup.map { d => 
             div(cls:="form-group")(
                 input(cls:="form-control", required, placeholder:=d, name:=d.toLowerCase, tpe:=d.split(" ")(0).toLowerCase),
                 div(cls:="help-block with-errors")
             )
           },
           div(cls:="checkbox")(
               label(
                   input(name:="remember", tpe:="checkbox", value:="Remember Me")("Remember Me")
               )
           ),
           refs.map { case (k,v) =>
             a(href:=v, cls:=s"btn btn-lg btn-${k} btn-block")(v.capitalize)
           }.toList
        )
     )
  }
}