package entrypoint.controllers


import entrypoint.controllers.converters.CashbackConverter
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc._
import usecase.createcashback.{CreateCashbackUsecase, FindLastCashbackUsecase}

import javax.inject.{Inject, Singleton}

@Singleton
class CashbackController @Inject()( val controllerComponents: ControllerComponents,
                                    val cashbackCreate: CreateCashbackUsecase,
                                    val findLastCashbackUsecase: FindLastCashbackUsecase,
                                    private val config: Configuration) extends BaseController {

  def create = Action { request: Request[AnyContent] =>
    val json = request.body.asJson
    val result = json match {
      case Some(value) =>
        val customer = CashbackConverter.converter(value, config.get[String]("perc.value"))
        cashbackCreate.execute(customer)
        Created
      case None => BadRequest
    }

    result
  }

  def find(code: String) = Action {
    import entrypoint.controllers.dto.response.CashbackWriteJson.cashbackWrite

    val result = findLastCashbackUsecase.execute(code)
    val json = Json.toJson(result)

    Ok(json)
  }
}
