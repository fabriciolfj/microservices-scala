package adapters.gateways

import adapters.providers.database.repositories.CashbackRepository
import entities.Cashback
import usecase.findcashback.GetLastRegistryCashbackProvider

import javax.inject.{Inject, Singleton}
import scala.concurrent.Await
import scala.concurrent.duration.{Duration}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class FindLastCashbackGateway @Inject()(repository: CashbackRepository) extends GetLastRegistryCashbackProvider {

  override def process(customer: String): Option[Cashback] = {
    val query = repository.query(customer)

    val result = try {
      Await.result(query, Duration.Inf) match {
        case Some(value) => Some(
          Cashback(code = value.code,
            credit = BigDecimal(value.credit),
            debit = BigDecimal(value.debit),
            date = value.getDate(),
            balance = BigDecimal(value.balance))
        )
        case None => None
      }
    } catch {
      case ex: Exception => throw new RuntimeException("Erro durante a consulta: " + ex.getMessage)
    }

    result
  }
}
