package entities

import java.time.LocalDateTime

case class Cashback(code: String,
                    credit: BigDecimal,
                    debit: BigDecimal,
                    date: LocalDateTime,
                    var balance: BigDecimal)
