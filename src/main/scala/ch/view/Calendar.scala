package ch.view

import scalatags._
import scalatags.Text.all.h1
import scalatags.Text.all.html
import scalatags.Text.all.stringFrag
import scalatags.Text.tags2
import scalatags.Text.all._
import scala.xml._
import ch.view.Header._
import ch.controller.Route
import ch.controller.Route._

class Calendar(username :String) {
  def build()={
    
    new Layout("Calendar",
        scripts("moment.min","fullcalendar.min", "app", "calendar"),
        links("fullcalendar.min.css", "app.css")).
      render(
          div(id:="wrapper")(
              tags2.nav(cls:="navbar navbar-default navbar-static-top",role:="navigation",style:="margin-bottom: 0")(
                  div(cls:="navbar-header")(
                      button(tpe:="button", cls:="navbar-toggle", data("toggle"):="collapse", data("target"):=".navbar-collapse")(
                          span(cls:="sr-only")("Toggle navigation"),
                          span(cls:="icon-bar"),
                          span(cls:="icon-bar"),
                          span(cls:="icon-bar")
                      ),
                      a(cls:="navbar-brand",href:="index.html")("SB Admin v2.0")
                  ),
                  ul(cls:="nav navbar-top-links navbar-right")(
                      li(cls:="dropdown")(
                          a(cls:="dropdown-toggle",data("toggle"):="dropdown",href:="#")(
                              i(cls:="fa fa-user fa-fw"),
                              (username),
                              i(cls:="fa fa-caret-down")
                          ),
                          ul(cls:="dropdown-menu dropdown-user")(
                              li(
                                  a(href:="#")(
                                      i(cls:="fa fa-gear fa-fw"),
                                      ("Settings")
                                  )
                              ),
                              li(cls:="divider"),
                              li(
                                  a(href:= Route(USER, LOGOUT) )(
                                      i(cls:="fa fa-sign-out fa-fw"),
                                      ("Logout")
                                  )
                              )
                          )
                      )
                  ),
      
                  div(cls:="navbar-default sidebar", role:="navigation")(
                      div(cls:="sidebar-nav navbar-collapse")(
                          ul(cls:="nav", id:="side-menu")(
                            li(
                                a(href:="index.html")(
                                    i(cls:="fa fa-dashboard fa-fw"),
                                    ("Dashboard")
                                 )
                              )
                          )
                      )
                  )
              ),
      
              div(id:="page-wrapper")(
                  div(cls:="container-fluid")(
                      div(cls:="row")(
                        div(cls:="col-lg-12")(
                          h1(cls:="page-header") //("Blank")
                        )
                      ),
                   div(cls:="row")(
                      div(cls:="col-lg-12")(
                        div(id:="calendar")
                      )
                   )
                 )
              )
           )
        )
//    new PrettyPrinter(80, 4).format(XML.loadString(tags.toString()))
  }
}
