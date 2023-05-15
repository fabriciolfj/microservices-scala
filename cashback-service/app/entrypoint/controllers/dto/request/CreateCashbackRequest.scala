package entrypoint.controllers.dto.request

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.Reads.minLength
import play.api.libs.json.{JsNumber, JsPath, Reads}
import utils.ReadsGenerics

case class CreateCashbackRequest(customer: String, value: BigDecimal)

object CreateCashbackRequest {
  implicit val cashbackReads: Reads[CreateCashbackRequest] = (
    (JsPath \ "customer").read[String](minLength[String](2)) and
      (JsPath \ "value").read[BigDecimal](ReadsGenerics.bigDecimalReads)
    )(CreateCashbackRequest.apply _)
}