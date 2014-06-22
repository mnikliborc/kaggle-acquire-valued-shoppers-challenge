package shp.preprocessing.features.extraction.extractor

import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.offer.Offer

abstract class Count(val predicates: List[FeatureSimpleExtractor.Predicate]) extends FeatureSimpleExtractor {
  def extract(transactions: List[Transaction], history: History, offer: Offer): String = {
    filter(transactions, history, offer).size.toString
  }
}