package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Sum
import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.extraction.extractor.Predicates

class SumOfCategoryPurchaseAmountInXDays(val days: Int)
  extends Sum(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferCategory), Transaction.getPurchaseAmount) {
  def featureName = s"SumOfCategoryPurchaseAmountIn${days}Days"
}