package providers.repository.mapper

import entities.Transaction
import org.mongodb.scala.Document

object DocumentMapper {

  def toDocument(transaction: Transaction) : Document = {
    Document("code" -> transaction.code, "customer" -> transaction.customer, "value" -> transaction.value,
    "type" -> transaction.operation.toString, "date" -> transaction.date.toString)
  }

}
