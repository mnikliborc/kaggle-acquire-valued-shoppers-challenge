package shp.preprocessing.features.extraction.extractor

import shp.preprocessing.features.model.offer.Offer
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.transaction.Transaction

trait FeatureMultiExtractor {
  def extract(transactions: List[Transaction], history: History, offer: Offer): List[String]
  def featureNames(): List[String]
}