package ch.view

import scalatags._
import scalatags.Text.all.h1
import scalatags.Text.all.html
import scalatags.Text.all.stringFrag
import scalatags.Text.tags2
import scalatags.Text.all._
import scala.xml._
import ch.view.Header._

class Calendar(username :String) {
  def build()={
    
    new Layout("Calendar",
        scripts("moment.min","fullcalendar.min", "app"),
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
                                  a(href:="login.html")(
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
                          h1(cls:="page-header")("Blank")
                        )
                      ),
                   div(cls:="row")(
                      div(cls:="col-lg-12")(
                        div(id:="calendar")
                      )
                   ),
                   div(cls:="modal", id:="timeForm", tabindex:="-1", role:="dialog", "aria-labelledby".attr:="myModalLabel", "aria-hidden".attr:="true")(
                     div(cls:="modal-dialog modal-sm")(
		           div(cls:="modal-content")(
		              div(cls:="modal-header", style:="background-color:#f5f5f5;border-top-left-radius: 5px;border-top-right-radius: 5px;")(
		                h3(cls:="panel-title modal-title")("Insert Work Day Time")
		              ),
		              div(cls:="modal-body")(
		                form(role:="form",data("toggle"):="validator")(
		                  fieldset(
		                    div(cls:="form-group")(
		                      input(tpe:="text",cls:="form-control", placeholder:="Hour in: hh:mm", id:="timeIn", name:="timeIn", pattern:="^[0-2][0-9]:[0-9][0-9]", maxlength:="5",required)
		                    ),
		                    div(cls:="form-group")(
		                      input(cls:="form-control", placeholder:="Pause: mm", name:="pause")
		                    ),
		                    div(cls:="form-group")(
		                      input(cls:="form-control", placeholder:="Hour out: hh:mm", name:="timeOut")
		                    ),
		                    div(cls:="modal-footer")(
		                      button(tpe:="button", cls:="btn btn-default btn-circle")(
		                        i(cls:="fa fa-check")
		                      ),
		                      button(tpe:="button", cls:="btn btn-default btn-circle",data("dismiss"):="modal")(
		                        i(cls:="fa fa-times")
		                      ),
					    button(tpe:="button", id:="toEventForm", cls:="btn btn-info btn-circle")(
		                        i(cls:="fa fa-angle-double-right ")
		                      )
		                    )
		                  )
		                )
		              )
		           )
                     )
                   ),
			 div(cls:="modal", id:="eventForm", tabindex:="-1", role:="dialog", "aria-labelledby".attr:="myModalLabel", "aria-hidden".attr:="true")(
                     div(cls:="modal-dialog modal-sm")(
		           div(cls:="modal-content")(
		              div(cls:="modal-header", style:="background-color:#f5f5f5;border-top-left-radius: 5px;border-top-right-radius: 5px;")(
		                h3(cls:="panel-title modal-title")("Insert Event Name")
		              ),
		              div(cls:="modal-body")(
		                form(role:="form")(
		                  fieldset(
		                    div(cls:="form-group")(
		                      label("Event Type:"),
              					    select(cls:="form-control")(
              					    	option("Holiday"),
              					    	option("Illness"),
              					    	option("Permission")					
              					    )
		                    ),
		                    div(cls:="form-group")(
		                      input(cls:="form-control", disabled:="", placeholder:="Hour in: hh:mm", name:="timeIn")
		                    ),
		                    div(cls:="form-group")(
		                      input(cls:="form-control", disabled:="", placeholder:="Hour out: hh:mm", name:="timeOut")
		                    ),
		                    div(cls:="modal-footer")(
		                      button(tpe:="button", id:="submitBtn", cls:="btn btn-default btn-circle")(
		                        i(cls:="fa fa-check")
		                      ),
		                      button(tpe:="button", cls:="btn btn-default btn-circle",data("dismiss"):="modal")(
		                        i(cls:="fa fa-times")
		                      ),
					                button(tpe:="button", id:="toTimeFormBtn", cls:="btn btn-danger btn-circle")(
		                        i(cls:="fa fa-angle-double-left")
		                      )
		                    )
		                  )
		                )
		             )
		           )
                     )
                   )
                 )
              )
           )
        )
//    new PrettyPrinter(80, 4).format(XML.loadString(tags.toString()))
  }
}
