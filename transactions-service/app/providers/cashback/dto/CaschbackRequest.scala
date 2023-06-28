package providers.cashback.dto

case class CaschbackRequest(customer: String, value: BigDecimal) {

}

object CaschbackRequest {

  import play.api.libs.json._

  implicit val caschbackRequestWrite = new Writes[CaschbackRequest] {

    override def writes(o: CaschbackRequest): JsValue = Json.obj(
      "customer" -> o.customer,
            "value" -> o.value)
  }
}
