package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Count
import shp.preprocessing.features.extraction.extractor.Predicates

class CountOfBrandReturn extends Count(List(Predicates.isReturn, Predicates.ofOfferBrand)) {
  def featureName = s"CountOfBrandReturn"
}