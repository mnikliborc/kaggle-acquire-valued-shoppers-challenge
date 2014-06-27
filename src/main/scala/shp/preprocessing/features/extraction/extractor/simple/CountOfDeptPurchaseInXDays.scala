package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Count
import shp.preprocessing.features.extraction.extractor.Predicates

class CountOfDeptPurchaseInXDays(val days: Int)
  extends Count(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferDept)) {
  def featureName = s"CountOfDeptPurchaseIn${days}Days"
}