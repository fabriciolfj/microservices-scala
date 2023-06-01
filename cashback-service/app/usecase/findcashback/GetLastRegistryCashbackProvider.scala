package usecase.findcashback

import entities.{Cashback, Customer}

trait GetLastRegistryCashbackProvider {

  def process(customer: String) : Option[Cashback]
}
