package providers.cashback

import com.google.inject.Singleton
import entities.Transaction
import play.api.Configuration
import providers.cashback.dto.CaschbackRequest
import providers.cashback.http.CashbackClient
import usecase.CreateCashbackProvider

import javax.inject.Inject

@Singleton
class CreateCashbackProviderImpl @Inject()(config: Configuration) extends CreateCashbackProvider {

  override def process(transaction: Transaction): Unit = {
    val request = CaschbackRequest(transaction.customer, transaction.value)
    CashbackClient.send(request, config.get[String]("cashback.uri"))
  }
}
