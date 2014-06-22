package shp.preprocessing.features.extraction.extractor

import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.offer.Offer

trait FeaturePostExtractor {
  def getFeatureValues(features: List[(String, String)], featureNames: List[String]): List[String] = {
    features.filter({
      case (value, name) => featureNames.contains(name)
    }).map(_._1)
  }

  def extract(features: Map[String, String], history: History, offer: Offer): String
  def featureName(): String
}