package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.FeatureSimpleExtractor
import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.offer.Offer
import shp.preprocessing.features.extraction.extractor.FeatureSimpleExtractor

class OfferValue extends FeatureSimpleExtractor {
  val predicates: List[FeatureSimpleExtractor.Predicate] = Nil
  def extract(transactions: List[Transaction], history: History, offer: Offer): String = {
    offer.offervalue.toString
  }

  def featureName = "OfferValue"
}