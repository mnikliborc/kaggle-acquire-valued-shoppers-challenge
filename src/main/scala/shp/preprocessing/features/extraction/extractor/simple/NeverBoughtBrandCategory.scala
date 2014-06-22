package shp.preprocessing.features.extraction.extractor.simple

import shp.preprocessing.features.extraction.extractor.Count
import shp.preprocessing.features.extraction.extractor.Predicates
import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.offer.Offer

class NeverBoughtBrandCategory extends Count(List(Predicates.ofOfferCategory, Predicates.ofOfferBrand)) {
  override def extract(transactions: List[Transaction], history: History, offer: Offer): String = {
    val count = super.extract(transactions, history, offer)
    if (count.equals("0")) "1" else "0"
  }
  def featureName() = "NeverBoughtBrandCategory"
}