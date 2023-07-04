package adapters.gateways

import adapters.providers.database.repositories.CashbackRepository
import entities.Cashback
import usecase.createcashback.SaveCashbackProvider
import scala.concurrent.ExecutionContext.Implicits.global

import javax.inject.{Inject, Singleton}

@Singleton
class SaveCashbackGateway @Inject()(private val repository: CashbackRepository) extends SaveCashbackProvider {

  override def process(code: String, cashback: Cashback): Unit = {
    repository.save(cashback, code)
  }
}
