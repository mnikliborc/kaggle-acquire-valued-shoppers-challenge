package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Sum
import shp.preprocessing.features.extraction.extractor.Predicates
import shp.preprocessing.features.model.transaction.Transaction

class SumOfDeptPurchaseAmountReturn extends Sum(List(Predicates.isReturn, Predicates.ofOfferDept), Transaction.getPurchaseAmount) {
  def featureName = s"SumOfDeptPurchaseAmountReturn"
}