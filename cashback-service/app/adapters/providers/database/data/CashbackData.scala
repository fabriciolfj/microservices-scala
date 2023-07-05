package adapters.providers.database.data

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

case class CashbackData(code: String,
                        customer: String,
                        credit: String,
                        debit: String,
                        date: String,
                        balance: String) {

  def getDate() : LocalDateTime = {
    LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))
  }
}

object CashbackData {

  implicit val cashbackDataReads: Reads[CashbackData] = (
    (JsPath \ "code").read[String] and
    (JsPath \ "customer").read[String] and
    (JsPath \ "credit").read[String] and
    (JsPath \ "debit").read[String] and
    (JsPath \ "date").read[String] and
    (JsPath \ "balance").read[String])(CashbackData.apply _)
}