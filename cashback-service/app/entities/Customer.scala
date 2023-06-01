package entities

case class Customer(code: String, cashbacks: List[Cashback]) {

  def sumCashback() = cashbacks.map(_.balance).sum
}
