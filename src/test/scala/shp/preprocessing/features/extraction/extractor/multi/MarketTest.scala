package shp.preprocessing.features.extraction.extractor.multi

import shp.preprocessing.features.model.transaction.CustomersTransactions
import org.scalatest.Matchers
import org.scalatest.FlatSpec
import shp.preprocessing.features.model.history.History
import org.joda.time.DateTime
import shp.preprocessing.features.model.offer.Offer
import shp.postprocessing.TestData

class MarketTest extends FlatSpec with Matchers {
  val extractor = new Market

  "Extractor" should "be correct" in {
    val history = new History("", "", "", "5", true, new DateTime(2012, 03, 03, 1, 1, 1))
    val offer = new Offer("", "4105", 0, "101116616", 0, "15266")

    val value = extractor.extract(Nil, history, offer)
    value.map(_.toInt).sum should be(1)
    value(3) should be("1")
  }
}