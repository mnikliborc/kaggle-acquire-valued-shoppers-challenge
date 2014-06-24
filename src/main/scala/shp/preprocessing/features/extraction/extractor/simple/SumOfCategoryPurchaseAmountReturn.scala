package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Sum
import shp.preprocessing.features.extraction.extractor.Predicates
import shp.preprocessing.features.model.transaction.Transaction

class SumOfCategoryPurchaseAmountReturn extends Sum(List(Predicates.isReturn, Predicates.ofOfferBrand), Transaction.getPurchaseAmount) {
  def featureName = s"SumOfCategoryPurchaseAmountReturn"
}