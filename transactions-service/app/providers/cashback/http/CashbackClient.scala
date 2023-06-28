package providers.cashback.http

import play.api.libs.json.Json
import providers.cashback.dto.CaschbackRequest
import sttp.client3._
import sttp.model.MediaType

object CashbackClient {


  def send(dto: CaschbackRequest, url: String): Unit = {
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
         println(s"fail request cashback: $error")
         throw new RuntimeException(error)
      },
      success => {
        println(s"receive success cashback: $success")
      }
    )
  }
}
