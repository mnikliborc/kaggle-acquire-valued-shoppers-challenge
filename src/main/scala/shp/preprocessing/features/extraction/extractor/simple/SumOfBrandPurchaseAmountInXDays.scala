package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Sum
import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.extraction.extractor.Predicates

class SumOfBrandPurchaseAmountInXDays(val days: Int)
  extends Sum(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferBrand), Transaction.getPurchaseAmount) {
  def featureName = s"SumOfBrandPurchaseAmountIn${days}Days"
}