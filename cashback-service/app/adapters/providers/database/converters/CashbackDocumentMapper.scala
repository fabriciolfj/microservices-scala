package adapters.providers.database.converters

import entities.Cashback
import org.mongodb.scala.Document


object CashbackDocumentMapper {

  def toDocumentCashback(entity: Cashback, customer: String) : Document = {
    Document(
      "code" -> entity.code,
      "customer" -> customer,
      "credit" -> entity.credit.toString(),
      "debit" -> entity.debit.toString(),
      "date" -> entity.date.toString,
      "balance" -> entity.balance.toString()
    )
  }

}
