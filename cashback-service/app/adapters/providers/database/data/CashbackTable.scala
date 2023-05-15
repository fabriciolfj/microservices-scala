package adapters.providers.database.data

import entities.{Cashback}
import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

import java.time.LocalDateTime

case class CashbackData(id: Long,
                        customer: String,
                        credit: BigDecimal,
                        debit: BigDecimal,
                        dateTime: LocalDateTime,
                        balance: BigDecimal)
class CashbackTable(tag: Tag) extends Table[CashbackData](tag, "cashback") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def customer = column[String]("customer")
  def credit = column[BigDecimal]("credit")
  def debit = column[BigDecimal]("debit")
  def dateTime = column[LocalDateTime]("date_time")
  def balance = column[BigDecimal]("balance")
  override def * = (id, customer, credit, debit, balance, dateTime) <> (CashbackData.tupled, CashbackData.unapply)
}

object CashbackTable {
  def apply(code: String, cash: Cashback)  = {
    val table = TableQuery[CashbackTable]
    val data = CashbackData(null, code, cash.credit, cash.debit, LocalDateTime.now(), cash.balance)
    table += data
  }
}
