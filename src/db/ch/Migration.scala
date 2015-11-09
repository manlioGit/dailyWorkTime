package ch

import ch.model.data.Driver.simple._
import ch.model._

object Migration extends App {
  
  if(args.length != 0){
	  val target = args(0)
	  
	  implicit val session = Database.forConfig(target).createSession()
	  
	  Users.table.schema.create
	  Events.table.schema.create
	  
  } else{
    println(s"Usage: ${getClass.getSimpleName} [db target]")
  }
  
}