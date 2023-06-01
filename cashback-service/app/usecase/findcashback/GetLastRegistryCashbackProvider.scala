package usecase.findcashback

import adapters.gateways.FindLastCashbackGateway
import com.google.inject.ImplementedBy
import entities.{Cashback, Customer}

@ImplementedBy(classOf[FindLastCashbackGateway])
trait GetLastRegistryCashbackProvider {

  def process(customer: String) : Option[Cashback]
}
