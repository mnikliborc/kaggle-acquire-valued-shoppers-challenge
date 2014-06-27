package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Sum
import shp.preprocessing.features.extraction.extractor.Predicates
import shp.preprocessing.features.model.transaction.Transaction

class QuantityOfDeptPurchaseAmountInXDays(val days: Int)
  extends Sum(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferDept), Transaction.getPurchaseQuantity) {
  def featureName = s"QuantityOfDeptPurchaseIn${days}Days"
}