package shp.preprocessing.features.extraction.extractor.post

import shp.preprocessing.features.extraction.extractor.FeaturePostExtractor
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.offer.Offer

class BrandToAllPurchaseAmountRatioInLastMonth extends FeaturePostExtractor {
  def extract(features: Map[String, String], history: History, offer: Offer): String = {
    val sumOfBrandPurchase = BigDecimal(features.getOrElse("SumOfBrandPurchaseAmountInLastMonth", "1").toDouble)
    if (sumOfBrandPurchase.equals(BigDecimal(0))) {
      "0"
    } else {
      val ratio = BigDecimal(features.getOrElse("SumOfPurchaseAmountInLastMonth", "1").toDouble) / sumOfBrandPurchase
      ratio.toInt.toString
    }
  }

  def featureName(): String = {
    "BrandToAllPurchaseAmountRatioInLastMonth"
  }
}