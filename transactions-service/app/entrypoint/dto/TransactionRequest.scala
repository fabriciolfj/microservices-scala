package entrypoint.dto

import entities.TypeOperation.TypeOperation
import entities.{Transaction, TypeOperation}
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.Reads.minLength
import play.api.libs.json.{JsError, JsPath, JsSuccess, JsValue, Reads}
import utils.ReadsGenerics

import java.time.LocalDateTime
import java.util.UUID

case class TransactionRequest(customer: String, operation: String, value: BigDecimal)

object TransactionRequest {
  implicit val transactionReads: Reads[TransactionRequest] = (
    (JsPath \ "customer").read[String](minLength[String](2)) and
      (JsPath \ "operation").read[String](minLength[String](2)) and
      (JsPath \ "value").read[BigDecimal](ReadsGenerics.bigDecimalReads)
    )(TransactionRequest.apply _)

  def toEntity(json: JsValue) : Transaction = {
    val result = json.validate[TransactionRequest]
    result match {
      case JsSuccess(value, path) => transform(value)
      case e: JsError => throw new RuntimeException("Error transform request. Details " + e.errors)
    }
  }

  private def transform(request: TransactionRequest) : Transaction = {
    Transaction(request.customer, operation = getTypeOperation(request.operation), value = request.value,
      code = UUID.randomUUID().toString, date = LocalDateTime.now())
  }

  private def getTypeOperation(value: String) : TypeOperation = {
    val result = TypeOperation.fromString(value)
    result match {
      case Some(value) => value
      case None => throw new RuntimeException("Type operation not found, value " + value)
    }
  }
}