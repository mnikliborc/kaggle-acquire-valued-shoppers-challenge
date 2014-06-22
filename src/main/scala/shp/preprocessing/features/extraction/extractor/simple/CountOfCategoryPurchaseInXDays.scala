package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Count
import shp.preprocessing.features.extraction.extractor.Predicates

class CountOfCategoryPurchaseInXDays(val days: Int) extends Count(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferCategory)) {
  def featureName = s"CountOfCategoryPurchaseIn${days}Days"
}