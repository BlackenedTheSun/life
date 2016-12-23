package controllers

import life.dto.LifeDto
import play.api.mvc.{Action, Controller}

/**
  * Created by Aliaksandr_Neuhen on 12/23/2016.
  */
object Application extends Controller {
  def index = Action {
    Ok(views.html.index(LifeDto(Set())))
  }
}
