package usecase.createcashback

import entities.Cashback

trait SaveCashbackProvider {

  def process(code: String, cashback: Cashback): Unit

}
