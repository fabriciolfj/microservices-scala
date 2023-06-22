package gateway

import entities.Transaction
import providers.repository.TransactionRepository
import usecase.SaveTransactionProvider
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class SaveTransactionGateway @Inject()(val repository: TransactionRepository) extends SaveTransactionProvider {

  override def process(transaction: Transaction): Unit = {
    repository.save(transaction)
  }
}
