package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.extraction.extractor.Sum

class SumOfPurchaseAmount extends Sum(List(), Transaction.getPurchaseAmount) {
  def featureName(): String = {
    "SumOfPurchaseAmount"
  }
}