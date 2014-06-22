package shp.preprocessing.features.model.transaction

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

case class Transaction(id: String, chain: String, dept: String, category: String, company: String, brand: String,
  date: DateTime, productSize: Double, productMeasure: String, purchaseQuantity: Int, purchaseAmount: Double)

object Transaction {
  val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")

  def of(line: String): Transaction = {
    val attrVals = line.split(",")
    val offerDate = formatter.parseDateTime(attrVals(6))
    new Transaction(attrVals(0), attrVals(1), attrVals(2), attrVals(3), attrVals(4), attrVals(5), offerDate, attrVals(7).toDouble, attrVals(8), attrVals(9).toInt, attrVals(10).toDouble)
  }

  def getPurchaseAmount(t: Transaction): Double = t.purchaseAmount
  def getPurchaseQuantity(t: Transaction): Double = t.purchaseQuantity
}