package shp.preprocessing.features.extraction.extractor

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import shp.preprocessing.features.model.transaction.CustomersTransactions
import shp.preprocessing.features.model.history.History
import org.joda.time.DateTime
import shp.preprocessing.features.model.offer.Offer
import shp.preprocessing.features.extraction.extractor.post.BrandToAllPurchaseAmountRatioInLastMonth
import shp.postprocessing.TestData

class BrandToAllPurchaseAmountRatioInLastMonthTest extends FlatSpec with Matchers {
  val extractor = new BrandToAllPurchaseAmountRatioInLastMonth

  "Extractor" should "be correct" in {
    val history = new History("", "", "", "", true, new DateTime(2012, 03, 03, 1, 1, 1))
    val offer = new Offer("", "", 0, "", 0, "15266")

    val value = extractor.extract(Map(("SumOfBrandPurchaseAmountInLastMonth" -> "10"), ("SumOfPurchaseAmountInLastMonth" -> "100")), history, offer)
    value should be("10")
  }
}