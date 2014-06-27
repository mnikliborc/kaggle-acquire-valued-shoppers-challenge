package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Count
import shp.preprocessing.features.extraction.extractor.Predicates

class CountOfCategoryReturn extends Count(List(Predicates.isReturn, Predicates.ofOfferCategory)) {
  def featureName = s"CountOfCategoryReturn"
}