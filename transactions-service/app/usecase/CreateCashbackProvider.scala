package usecase

import com.google.inject.ImplementedBy
import entities.Transaction
import providers.cashback.CreateCashbackProviderImpl

@ImplementedBy(classOf[CreateCashbackProviderImpl])
trait CreateCashbackProvider {

  def process(transaction: Transaction) : Unit

}
