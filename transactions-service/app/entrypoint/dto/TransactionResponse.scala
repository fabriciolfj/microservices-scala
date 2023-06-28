package entrypoint.dto

import entities.Transaction

case class TransactionResponse(val code: String) {
}

object TransactionResponse {
  import play.api.libs.json._

  implicit val transactionResponseWrite = new Writes[TransactionResponse] {

    override def writes(o: TransactionResponse): JsValue = Json.obj(
      "code" -> o.code)
  }
  def toResponse(transaction: Transaction): TransactionResponse = {
    TransactionResponse(transaction.code)
  }
}
