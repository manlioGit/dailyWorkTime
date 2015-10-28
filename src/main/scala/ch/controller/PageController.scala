package ch.controller

import scalatags.Text.all.body
import scalatags.Text.all.h1
import scalatags.Text.all.html
import scalatags.Text.all.stringFrag
import scalatags.Text.tags2
import ch.view.Calendar
import ch.auth.AuthenticationSupport

class PageController extends MainStack /*with AuthenticationSupport*/ {

//  before(){
//    requireLogin
//  }
  
  get("/*") {
    contentType = "text/html"
    
    "<!DOCTYPE html>" + new Calendar(params("user")).build()
  }
}
