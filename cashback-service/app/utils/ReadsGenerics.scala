package utils

import play.api.libs.json.{JsNumber, Reads}

object ReadsGenerics {
  implicit val bigDecimalReads: Reads[BigDecimal] = Reads.of[JsNumber].map(_.value)

}

