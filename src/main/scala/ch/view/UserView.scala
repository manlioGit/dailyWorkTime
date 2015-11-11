package ch.view

import scalatags._
import scalatags.Text.all._
import scalatags.Text.tags2
import scala.xml.Elem
import scala.xml.XML
import ch.view.Header._
import ch.controller.Route._

class UserView(title: String, data: List[String], refs:Map[String,String]) {
   
  def render()={
    new Layout(title, scripts(USER), links()).render(
        div(cls:="container")(
            div(cls:="row")(
                div(cls:="col-md-4 col-md-offset-4")(
                    div(cls:="login-panel panel panel-default")(
                         div(cls:="panel-heading")(
                             h3(cls:="panel-title")(title.capitalize)
                         ),
                     div(cls:="panel-body")(
                         new LoginForm(data, refs, title).render()
                     )
                    )
               )
           )
       )
     )
   }
}