import life.LifeHandler
import life.dto.{CellDto, LifeDto}
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import play.api.ApplicationLoader.Context
import play.api.mvc.Action
import play.api.routing.Router
import play.api.routing.sird._
import play.api.mvc.Results._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import views._

import scala.concurrent.Future

class AppLoader extends ApplicationLoader{
  val handler: LifeHandler = new LifeHandler

  implicit val cellWrites: Writes[CellDto] = (
    (JsPath \ "x").write[Int] and
    (JsPath \ "y").write[Int]
  )(unlift(CellDto.unapply))

  implicit val lifeWrites: Writes[LifeDto] = (
    (JsPath \ "aliveCells").write[Set[CellDto]] and
    (JsPath \ "epochNum").write[Int]
  )(unlift(LifeDto.unapply))

  implicit val cellReads: Reads[CellDto] = (
    (JsPath \ "x").read[Int] and
    (JsPath \ "y").read[Int]
  )(CellDto.apply _)

  implicit val lifeReads: Reads[LifeDto] = (
    (JsPath \ "aliveCells").read[Set[CellDto]] and
    (JsPath \ "epochNum").read[Int]
  )(LifeDto.apply _)

  override def load(context: Context): Application = new BuiltInComponentsFromContext(context) {

    val router = Router.from {
      case GET(p"/life") => Action {
        Ok(html.index(LifeDto(Set(), 0)))
      }
      case POST(p"/life") => Action { implicit request =>
          request.body.asJson.flatMap(mapLifeDto) match {
            case Some(lifeDto) => Ok(html.field(handler.handlePost(lifeDto)))
            case None => UnprocessableEntity("Incorrect request body")
        }
      }
    }
  }.application

  def mapLifeDto(json : JsValue): Option[LifeDto] = {
    json.validate[LifeDto] match {
      case JsSuccess(value, _) => Some(value)
      case _ => None
    }
  }
}
