package adapters.gateways

import adapters.providers.database.repositories.CashbackRepository
import entities.Cashback
import usecase.findcashback.GetLastRegistryCashbackProvider
import slick.jdbc.PostgresProfile.api._

import javax.inject.{Inject, Singleton}
import scala.concurrent.Await
import scala.concurrent.duration.{Duration}

@Singleton
class FindLastCashbackGateway @Inject()(repository: CashbackRepository) extends GetLastRegistryCashbackProvider {

  override def process(customer: String): Option[Cashback] = {
    val sql = sql"SELECT * FROM cashback ORDER BY date_time DESC LIMIT 1"
    val query = repository.query(sql)

    val result = try {
      Await.result(query, Duration.Inf) match {
        case Some(value) => Some(Cashback(credit = value.credit, debit = value.debit, balance = value.balance, date = value.dateTime))
        case None => None
      }
    } catch {
      case ex: Exception => throw new RuntimeException("Erro durante a consulta: " + ex.getMessage)
    }

    result
  }
}
