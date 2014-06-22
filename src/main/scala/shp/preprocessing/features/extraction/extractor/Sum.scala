package shp.preprocessing.features.extraction.extractor

import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.offer.Offer
import shp.preprocessing.features.model.transaction.Transaction

abstract class Sum(val predicates: List[FeatureSimpleExtractor.Predicate], val sumOf: (Transaction) => (Double)) extends FeatureSimpleExtractor {
  def extract(transactions: List[Transaction], history: History, offer: Offer): String = {
    def notReturn(t: Transaction) = t.purchaseAmount > 0

    val amounts = filter(transactions, history, offer).map(sumOf)
    val avg = BigDecimal(amounts.sum)
    avg.setScale(2, BigDecimal.RoundingMode.CEILING).toString
  }
}