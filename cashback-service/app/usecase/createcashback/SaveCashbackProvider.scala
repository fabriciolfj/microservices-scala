package usecase.createcashback

import adapters.gateways.SaveCashbackGateway
import com.google.inject.ImplementedBy
import entities.Cashback

@ImplementedBy(classOf[SaveCashbackGateway])
trait SaveCashbackProvider {

  def process(code: String, cashback: Cashback): Unit

}
