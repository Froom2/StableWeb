package controllers

import play.api.mvc._
import views.html.index

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}

object Application extends Application