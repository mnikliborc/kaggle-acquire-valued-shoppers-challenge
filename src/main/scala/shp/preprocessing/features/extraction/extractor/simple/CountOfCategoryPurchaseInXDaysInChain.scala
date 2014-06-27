package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Count
import shp.preprocessing.features.extraction.extractor.Predicates

class CountOfCategoryPurchaseInXDaysInChain(val days: Int) extends Count(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferCategory, Predicates.ofChain)) {
  def featureName = s"CountOfCategoryPurchaseIn${days}DaysInChain"
}