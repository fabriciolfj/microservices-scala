package usecase.createcashback

import entities.{Cashback, Customer}
import usecase.findcashback.GetLastRegistryCashbackProvider

import javax.inject.{Inject, Singleton}

@Singleton
class CreateCashbackUsecase @Inject()(private val saveCashbackProvider: SaveCashbackProvider,
                                      private val find: GetLastRegistryCashbackProvider) {

  def execute(customer: Customer): Unit = {
    val result = find.process(customer.code) match {
      case Some(it) => calc(cashbacks = customer.cashbacks, it.balance)
      case None => calc(cashbacks = customer.cashbacks, balance = BigDecimal.apply(0))
    }

    result.foreach(saveCashbackProvider.process(customer.code, _))
  }

  private def calc(cashbacks: List[Cashback], balance: BigDecimal) : List[Cashback] = {
    cashbacks.map(c => {
      val result = c.credit.-(c.debit).+(balance)
      c.balance = result
      c
    })
  }

}
