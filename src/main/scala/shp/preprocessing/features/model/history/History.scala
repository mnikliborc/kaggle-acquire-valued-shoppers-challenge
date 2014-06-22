package shp.preprocessing.features.model.history

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

case class History(id: String, chain: String, offer: String, market: String, repeater: Boolean, offerDate: DateTime)

object History {
  val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")

  def ofTrain(line: String): History = {
    val attrVals = line.split(",")

    val repeater = attrVals(5) match {
      case "t" => true
      case "f" => false
    }

    new History(attrVals(0), attrVals(1), attrVals(2), attrVals(3), repeater, formatter.parseDateTime(attrVals(6)))
  }

  def ofTest(line: String): History = {
    val attrVals = line.split(",")
    new History(attrVals(0), attrVals(1), attrVals(2), attrVals(3), false, formatter.parseDateTime(attrVals(4)))
  }
}

