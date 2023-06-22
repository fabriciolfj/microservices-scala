package usecase

import com.google.inject.ImplementedBy
import entities.Transaction
import gateway.SaveTransactionGateway


@ImplementedBy(classOf[SaveTransactionGateway])
trait SaveTransactionProvider {

  def process(transaction: Transaction) : Unit

}
