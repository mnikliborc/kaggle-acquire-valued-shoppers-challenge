package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Sum
import shp.preprocessing.features.extraction.extractor.Predicates
import shp.preprocessing.features.model.transaction.Transaction

class SumOfBrandPurchaseAmountInXDaysInChain(val days: Int)
  extends Sum(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferBrand, Predicates.ofChain), Transaction.getPurchaseAmount) {
  def featureName = s"SumOfBrandPurchaseAmountIn${days}DaysInChain"
}