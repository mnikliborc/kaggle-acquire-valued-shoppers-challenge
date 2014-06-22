package shp.preprocessing.features.extraction.extractor.items

import org.scalatest.FlatSpec
import shp.preprocessing.features.model.transaction.CustomersTransactions
import org.scalatest.Matchers
import shp.preprocessing.features.model.history.History
import org.joda.time.DateTime
import shp.preprocessing.features.model.offer.Offer
import shp.preprocessing.features.extraction.extractor.simple.QuantityOfCategoryPurchaseInXDays
import shp.postprocessing.TestData

class QuantityOfProductsBoughtOfOfferedCategoryInLastMonthTest extends FlatSpec with Matchers with TestData {
  val stream = new CustomersTransactions(fewTransactions)
  val extractor = new QuantityOfCategoryPurchaseInXDays(30)

  "Extractor" should "be correct" in {
    val history = new History("", "", "", "", true, new DateTime(2012, 03, 06, 1, 1, 1))
    val offer = new Offer("", "4105", 0, "101116616", 0, "15266")

    val transactions = stream.head
    val value = extractor.extract(transactions, history, offer)
    value should be("10.00")
  }
}