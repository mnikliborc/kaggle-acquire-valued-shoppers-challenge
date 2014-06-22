package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Sum
import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.extraction.extractor.Predicates

class QuantityOfCategoryPurchaseInXDays(val days: Int)
  extends Sum(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferCategory), Transaction.getPurchaseQuantity) {
  def featureName = s"QuantityOfBrandPurchaseIn${days}Days"
}