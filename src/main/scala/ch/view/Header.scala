package ch.view

import scalatags.Text.all._
import scala.util.Random

object Header {
  
   def scripts(s:String*) :List[Tag] = {
     (List("jquery.min","bootstrap.min","metisMenu.min", "validator.min", "sb-admin-2") ++ s).
       map { s =>  script(src:=s"../js/${s}.js?" + Random.nextInt(Integer.MAX_VALUE)) }
   }
   
   def links(s:String*) : List[Tag] = {
     (List("bootstrap.min.css","sb-admin-2.css","font-awesome.min.css") ++ s).
       map { s =>  link(href:=s"../css/${s}", rel:="stylesheet",tpe:="text/css") }
   }
   
   def metas : List[Tag] = {
     List(meta(charset:="utf-8"),
          meta(httpEquiv:="X-UA-Compatible",content:="IE:=edge"),
          meta(name:="viewport",content:="width=device-width, initial-scale:=1"),
          meta(name:="description", content:=""),
          meta(name:="author", content:=""))
   }
}