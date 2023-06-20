package entities

object TypeOperation extends Enumeration {
  type TypeOperation = Value

  val DEBIT, CREDIT = Value

  def fromString(value: String): Option[TypeOperation] = {
    values.find(_.toString.equalsIgnoreCase(value))
  }

}
