package entrypoint.controllers

import entrypoint.dto.TransactionRequest
import play.api.mvc._
import usecase.CreateTransactionUsecase

import javax.inject.{Inject, Singleton}

@Singleton
class TransactionsController @Inject()(val controllerComponents: ControllerComponents,
                                       val createTransaction: CreateTransactionUsecase) extends BaseController {

  def create : Action[AnyContent] = Action { request : Request[AnyContent] =>
    val json = request.body.asJson
    val result = json match {
      case Some(value) =>
        val transaction = TransactionRequest.toEntity(value)
        createTransaction.execute(transaction)
      case None => BadRequest
    }

    Ok
  }
}
