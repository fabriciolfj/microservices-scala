package providers.cashback.http

import org.slf4j.LoggerFactory
import play.api.libs.json.Json
import providers.cashback.dto.CaschbackRequest
import sttp.client3._
import sttp.model.MediaType

object CashbackClient {


  def send(dto: CaschbackRequest, url: String): Unit = {
    val logger = LoggerFactory.getLogger(getClass)
    import CaschbackRequest.caschbackRequestWrite

    val requestBody = Json.toJson(dto).toString()
    val request = basicRequest
      .body(requestBody)
      .contentType(MediaType.ApplicationJson)
      .post(uri"$url")

    val backend = HttpURLConnectionBackend()

    val response = request.send(backend)

    response.body.fold(
      error => {
        logger.info(s"fail request cashback: $error")
      },
      success => {
        logger.info(s"receive success cashback: $success")
      }
    )
  }
}
