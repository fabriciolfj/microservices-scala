package entrypoint.controllers.converters

import entities.{Cashback, Customer}
import entrypoint.controllers.dto.request.CreateCashbackRequest
import play.api.libs.json.{JsError, JsSuccess, JsValue}

import java.time.LocalDateTime
import java.util.UUID

object CashbackConverter {

  def converter(json: JsValue) = {
    val result = json.validate[CreateCashbackRequest]
    result match {
      case JsSuccess(value, path) => transform(value)
      case e: JsError => throw new RuntimeException("Error transform request. Details " + e.errors)
    }
  }

  private def transform(request: CreateCashbackRequest) : Customer = {
    val cashback = Cashback(UUID.randomUUID().toString, request.value, BigDecimal(0), LocalDateTime.now(), request.value)
    Customer(request.customer, List(cashback))
  }
}
