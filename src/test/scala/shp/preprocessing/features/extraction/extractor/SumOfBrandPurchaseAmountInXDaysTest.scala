package shp.preprocessing.features.extraction.extractor

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import shp.preprocessing.features.model.transaction.CustomersTransactions
import shp.preprocessing.features.model.history.History
import org.joda.time.DateTime
import shp.preprocessing.features.model.offer.Offer
import shp.preprocessing.features.extraction.extractor.simple.SumOfBrandPurchaseAmountInXDays
import shp.postprocessing.TestData

class SumOfBrandPurchaseAmountInXDaysTest extends FlatSpec with Matchers with TestData {
  val offer = new Offer("", "4105", 0, "101116616", 0, "15266")

  "Extractor" should "be correct for 3 days" in {
    val stream = new CustomersTransactions(fewTransactions)
    val extractor = new SumOfBrandPurchaseAmountInXDays(3)
    val history = new History("", "", "", "", true, new DateTime(2012, 03, 06, 1, 1, 1))

    val transactions = stream.head
    val value = extractor.extract(transactions, history, offer)
    value should be("31.23")
  }

  "Extractor" should "be correct for 30 days" in {
    val stream = new CustomersTransactions(fewTransactions)
    val extractor = new SumOfBrandPurchaseAmountInXDays(30)
    val history = new History("", "", "", "", true, new DateTime(2012, 03, 03, 1, 1, 1))

    val transactions = stream.head
    val value = extractor.extract(transactions, history, offer)
    value should be("2.99")
  }
}