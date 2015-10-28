package ch.view

import scalatags.Text.all._
import scalatags.Text.tags2

import ch.view.Header._

class Layout(private val title: String, scripts: Seq[Modifier], links: Seq[Modifier]) {
  
  def render(content:Modifier*) = {
    "<!DOCTYPE html>" + html(lang:="en")(
        head(metas, tags2.title()(title), links, scripts),
      body(
          content
      )
    )
  }
}