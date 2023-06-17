package entrypoint.controllers.dto.response

import entities.Cashback

object CashbackWriteJson {
  import play.api.libs.json._

  implicit val cashbackWrite = new Writes[Cashback] {

    override def writes(o: Cashback): JsValue = Json.obj(
      "date" -> o.date,
      "balance" -> o.balance)
  }
}
