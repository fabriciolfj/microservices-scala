package entities

import entities.TypeOperation.TypeOperation

case class Transaction(customer: String, value: BigDecimal, operation: TypeOperation)
