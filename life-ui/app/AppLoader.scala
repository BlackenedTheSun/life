import life.LifeHandler
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import play.api.ApplicationLoader.Context
import play.api.mvc.Action
import play.api.routing.Router
import play.api.routing.sird._
import play.api.mvc.Results._
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Future

class AppLoader extends ApplicationLoader{
  val handler: LifeHandler = new LifeHandler

  override def load(context: Context): Application = new BuiltInComponentsFromContext(context) {

    val router = Router.from {
      case GET(p"/life") => Action {
        Ok("GET request")
      }
      case POST(p"/life") => Action.async {
        Future {
          Ok("POST request")
        }
      }
    }
  }.application
}
