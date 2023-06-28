package entrypoint.controllers

import entrypoint.dto.{TransactionRequest, TransactionResponse}
import play.api.libs.json.Json
import play.api.mvc._
import usecase.CreateTransactionUsecase

import javax.inject.{Inject, Singleton}

@Singleton
class TransactionsController @Inject()(val controllerComponents: ControllerComponents,
                                       val createTransaction: CreateTransactionUsecase) extends BaseController {

  def create : Action[AnyContent] = Action { request : Request[AnyContent] =>
    import entrypoint.dto.TransactionResponse.transactionResponseWrite

    val json = request.body.asJson
     json match {
      case Some(value) =>
        val result = createTransaction.execute(TransactionRequest.toEntity(value))
        val dto = TransactionResponse.toResponse(result)
        Ok(Json.toJson(dto))
      case None => BadRequest
    }
  }
}
