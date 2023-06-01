package adapters.providers.database.data

import entities.Cashback
import slick.jdbc.GetResult
import slick.lifted.{ProvenShape, Tag}
import slick.jdbc.PostgresProfile.api._

import java.time.LocalDateTime

case class CashbackData(id: Option[Long],
                        customer: String,
                        credit: BigDecimal,
                        debit: BigDecimal,
                        balance: BigDecimal,
                        dateTime: LocalDateTime)

object CashbackData {
  implicit val getResult: GetResult[CashbackData] = GetResult(r => CashbackData(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))

  implicit val localDateTimeGetResult: GetResult[LocalDateTime] =
    GetResult(r => r.nextTimestamp().toLocalDateTime())
}

class CashbackTable(tag: Tag) extends Table[CashbackData](tag, "cashback") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def customer = column[String]("customer")
  def credit = column[BigDecimal]("credit")
  def debit = column[BigDecimal]("debit")
  def balance = column[BigDecimal]("balance")
  def dateTime = column[LocalDateTime]("date_time")

  override def * : ProvenShape[CashbackData] = (id.?, customer, credit, debit, balance, dateTime).shaped <> ((CashbackData.apply _).tupled, CashbackData.unapply)
}

object CashbackTable {

  def apply(id: Option[Long], customer: String, credit: BigDecimal, debit: BigDecimal, balance: BigDecimal, dateTime: LocalDateTime) =
    CashbackData(id, customer, credit, debit, balance, dateTime)

  def create(code: String, cash: Cashback)  = {
    val table = TableQuery[CashbackTable]
    val data = CashbackData(null, code, cash.credit, cash.debit, cash.balance, LocalDateTime.now())
    table += data
  }

}

