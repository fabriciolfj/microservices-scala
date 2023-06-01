package entities

import java.time.LocalDateTime

case class Cashback(credit: BigDecimal,
                   debit: BigDecimal,
                   date: LocalDateTime,
                    var balance: BigDecimal)
