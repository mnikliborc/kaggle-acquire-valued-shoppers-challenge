package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Count
import shp.preprocessing.features.extraction.extractor.Predicates

class CountOfBrandPurchaseInXDaysInChain(val days: Int) extends Count(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferBrand, Predicates.ofChain)) {
  def featureName = s"CountOfBrandPurchaseIn${days}InChain"
}