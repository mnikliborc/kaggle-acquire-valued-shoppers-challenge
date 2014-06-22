package shp.preprocessing.features.extraction.extractor

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.transaction.CustomersTransactions
import org.joda.time.DateTime
import shp.preprocessing.features.model.offer.Offer
import shp.preprocessing.features.extraction.extractor.simple.SumOfPurchaseAmount
import shp.postprocessing.TestData
import shp.preprocessing.features.extraction.extractor.simple.SumOfCategoryPurchaseAmountInXDays

class SumOfCategoryPurchaseAmountInXDaysTest extends FlatSpec with Matchers with TestData {
  val offer = new Offer("", "3611", 0, "", 0, "")

  "Extractor" should "be correct for 3 days" in {
    val stream = new CustomersTransactions(fewTransactions)
    val extractor = new SumOfCategoryPurchaseAmountInXDays(3)
    val history = new History("", "", "", "", true, new DateTime(2012, 03, 06, 1, 1, 1))

    val transactions = stream.head
    val value = extractor.extract(transactions, history, offer)
    value should be("4.49")
  }

  "Extractor" should "be correct for 30 days" in {
    val stream = new CustomersTransactions(fewTransactions)
    val extractor = new SumOfCategoryPurchaseAmountInXDays(30)
    val history = new History("", "", "", "", true, new DateTime(2012, 03, 03, 1, 1, 1))

    val transactions = stream.head
    val value = extractor.extract(transactions, history, offer)
    value should be("6.29")
  }
}