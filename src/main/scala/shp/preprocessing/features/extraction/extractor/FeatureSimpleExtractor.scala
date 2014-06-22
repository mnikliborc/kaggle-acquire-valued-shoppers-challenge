package shp.preprocessing.features.extraction.extractor

import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.offer.Offer

/**
 * features:
 * Xavg sum of purchaseAmount over last month
 * Xavg sum of purchaseAmount over last three days
 * Xsum of purchaseAmount in last month
 * Xsum of purchaseAmount in last three days
 * avg productSize of
 *
 * avg sum of purchaseAmount over month (of offered brand)
 * Xsum of purchaseAmount in last month (of offered brand)
 * Xsum of purchaseAmount in last three days (of offered brand)
 *
 * sum of purchaseAmount of B(brand) in C(product category) in S(season) from T1 to T2 days before offer
 * T1, T2 = 0, 3, 30, 90
 * C = all, offered, offered'
 * B = all, offered, offered'
 * S = winter, summer, spring, autumn?
 *
 * isWinter
 * isSummer
 * isSpring
 * isAutumn
 * Xis offered on weekend
 * offervalue/offerquantity
 * czy kupuje z danej kategorii ale innych brandow?
 * !!! srednia/dynamika cena jednostkowa (purchaseamount/purchasequantity) ostatnich K zakupionych przedmiotow z brandu B w kategorii C
 * cena jednostkowa ostatniego zakupu / (offervalue/offerquantity)
 * znalezc mapowanie category -> department i uzywac department zamiast category?
 * !!!liczba zwroconych produktow brandu B w kategorii C w okresie P przed oferta?
 * !!!liczba kupionych produktow brandu B w kategorii C w okresie P przed oferta ale po ostatnim zwroconym produkcie z B C?
 * price per unit (transaction.purchaseAmount / t.purchaseQuantity) / t.productSize
 * estymacja ceny rynkowej danegeo produktu z offers.csv (lepiej nawet: w okresie miesiac przed zakupem)
 */
trait FeatureSimpleExtractor {
  def extract(transactions: List[Transaction], history: History, offer: Offer): String
  def featureName(): String

  val predicates: List[FeatureSimpleExtractor.Predicate]

  protected def filter(transactions: List[Transaction], history: History, offer: Offer): List[Transaction] = {
    predicates.foldLeft(transactions) {
      case (trs, predicate) => trs.filter(predicate(_, history, offer))
    }
  }
}

object FeatureSimpleExtractor {
  type Predicate = (Transaction, History, Offer) => Boolean
}