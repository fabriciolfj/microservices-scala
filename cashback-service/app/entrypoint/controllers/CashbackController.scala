package entrypoint.controllers


import entrypoint.controllers.converters.CreateCashbackRequestConverter
import play.api.mvc._
import usecase.createcashback.CreateCashbackUsecase

import javax.inject.{Inject, Singleton}

@Singleton
class CashbackController @Inject()( val controllerComponents: ControllerComponents,
                                    val cashbackCreate: CreateCashbackUsecase) extends BaseController {

  def create = Action { request: Request[AnyContent] =>
    val json = request.body.asJson
    val result = json match {
      case Some(value) =>
        val request = CreateCashbackRequestConverter.converter(value)
        cashbackCreate.execute(request)
        Created
      case None => BadRequest
    }

    result
  }
}
