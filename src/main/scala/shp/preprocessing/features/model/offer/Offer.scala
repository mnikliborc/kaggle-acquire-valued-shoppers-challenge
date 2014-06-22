package shp.preprocessing.features.model.offer

case class Offer(offer: String, category: String, quantity: Int, company: String, offervalue: Double, brand: String)

object Offer {
  def of(line: String): Offer = {
    val attrVals = line.split(",")
    new Offer(attrVals(0), attrVals(1), attrVals(2).toInt, attrVals(3), attrVals(4).toDouble, attrVals(5))
  }
}