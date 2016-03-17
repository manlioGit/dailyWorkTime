package ch.view

import scalatags._
import scalatags.Text.all._

class Modal(kind: String){
  
  def build() = {
    
    val title = Map("single" -> "Insert Work Day Time", "period" -> "Insert Event Name")
    val arrow = Map("single" -> "right", "period" -> "left")
    val color = Map("single" -> "info", "period" -> "danger")
    val next  = Map("single" -> "period", "period" -> "single")
    val elements  = Map("single" -> Seq(
                                         div(cls:="form-group")(
                                            input(tpe:="text",cls:="form-control", placeholder:="Hour in: hh:mm", id:="timeIn", name:="timeIn", pattern:="^[0-2][0-9]:[0-9][0-9]", maxlength:="5",required)
                                          ),
                                          div(cls:="form-group")(
                                            input(cls:="form-control", placeholder:="Pause: mm", name:="pause")
                                          ),
                                          div(cls:="form-group")(
                                            input(cls:="form-control", placeholder:="Hour out: hh:mm", name:="timeOut")
                                          )
                                        ), 
                        "period" -> Seq(
                                        div(cls:="form-group")(
                                          label("Event Type:"),
                              					    select(cls:="form-control")(
                              					    	option("Holiday"),
                              					    	option("Illness"),
                              					    	option("Permission")					
                              					    )
                  		                    )
                                        )
                        )
    
    div(cls:="modal", id:="timeForm", tabindex:="-1", role:="dialog", "aria-labelledby".attr:="myModalLabel")(
       div(cls:="modal-dialog modal-sm")(
         div(cls:="modal-content")(
            div(cls:="modal-header", style:="background-color:#f5f5f5;border-top-left-radius: 5px;border-top-right-radius: 5px;")(
              h3(cls:="panel-title modal-title")(title(kind))
            ),
            div(cls:="modal-body")(
              form(role:="form",data("toggle"):="validator")(
                fieldset(
                  elements(kind)
                  ,
                  div(cls:="modal-footer")(
                    button(tpe:="button", cls:="btn btn-default btn-circle")(
                      i(cls:="fa fa-check")
                    ),
                    button(tpe:="button", cls:="btn btn-default btn-circle",data("dismiss"):="modal")(
                      i(cls:="fa fa-times")
                    ),
                    button(tpe:="button", id:="switch", cls:=s"btn btn-${color(kind)} btn-circle", data.next:=s"${next(kind)}")(
                        i(cls:=s"fa fa-angle-double-${arrow(kind)}")
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