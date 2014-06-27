package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Sum
import shp.preprocessing.features.extraction.extractor.Predicates
import shp.preprocessing.features.model.transaction.Transaction

class SumOfDeptPurchaseAmountInXDays(val days: Int)
  extends Sum(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferDept), Transaction.getPurchaseAmount) {
  def featureName = s"SumOfDeptPurchaseAmountIn${days}Days"
}