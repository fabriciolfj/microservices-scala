package adapters.providers.database.data

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}
import utils.ReadsGenerics

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

case class CashbackData(code: String,
                        customer: String,
                        credit: BigDecimal,
                        debit: BigDecimal,
                        date: String,
                        balance: BigDecimal) {

  def getDate() =  LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
}

object CashbackData {

  implicit val cashbackDataReads: Reads[CashbackData] = (
    (JsPath \ "code").read[String] and
    (JsPath \ "customer").read[String] and
    (JsPath \ "credit").read[BigDecimal](ReadsGenerics.bigDecimalReads) and
    (JsPath \ "debit").read[BigDecimal](ReadsGenerics.bigDecimalReads) and
    (JsPath \ "date").read[String] and
    (JsPath \ "balance").read[BigDecimal](ReadsGenerics.bigDecimalReads))(CashbackData.apply _)
}