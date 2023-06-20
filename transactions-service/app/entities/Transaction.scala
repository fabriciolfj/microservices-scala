package entities

import entities.TypeOperation.TypeOperation

import java.time.{LocalDate, LocalDateTime}

case class Transaction(customer: String,
                       value: BigDecimal,
                       operation: TypeOperation,
                       code: String,
                       date: LocalDateTime)
