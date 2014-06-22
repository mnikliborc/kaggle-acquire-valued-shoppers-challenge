package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.FeatureSimpleExtractor
import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.offer.Offer
import shp.preprocessing.features.extraction.extractor.FeatureSimpleExtractor
import shp.preprocessing.features.extraction.extractor.Predicates

class HasBoughtProductOfOfferedCompanyBrandCategory extends FeatureSimpleExtractor {
  val predicates: List[FeatureSimpleExtractor.Predicate] = List(Predicates.ofOfferBrand, Predicates.ofOfferCategory, Predicates.isNotReturn)

  def extract(transactions: List[Transaction], history: History, offer: Offer): String = {
    if (filter(transactions, history, offer).size > 0) {
      "1"
    } else {
      "0"
    }
  }

  def featureName() = "HasBoughtProductOfOfferedCompanyBrandCategory"
}