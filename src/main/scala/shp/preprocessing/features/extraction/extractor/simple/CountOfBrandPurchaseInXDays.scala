package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Count
import shp.preprocessing.features.extraction.extractor.Predicates

class CountOfBrandPurchaseInXDays(val days: Int) extends Count(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferBrand)) {
  def featureName = s"CountOfBrandPurchaseIn${days}Days"
}