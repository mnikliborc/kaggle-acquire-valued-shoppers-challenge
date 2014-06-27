package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Count
import shp.preprocessing.features.extraction.extractor.Predicates

class CountOfDeptReturn extends Count(List(Predicates.isReturn, Predicates.ofOfferDept)) {
  def featureName = s"CountOfDeptReturn"
}