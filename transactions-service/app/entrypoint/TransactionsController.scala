package entrypoint


import entrypoint.dto.TransactionRequest
import play.api.mvc.{Action, _}

import javax.inject.{Inject, Singleton}

@Singleton
class TransactionsController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def create : Action[AnyContent] = Action { request : Request[AnyContent] =>
    val json = request.body.asJson
    val result = json match {
      case Some(value) => TransactionRequest.toEntity(value)
      case None => BadRequest
    }
    Ok
  }
}
