package shp.preprocessing.features.extraction.extractor.multi

import shp.preprocessing.features.extraction.extractor.FeatureMultiExtractor
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.offer.Offer
import shp.preprocessing.features.model.transaction.Transaction
import scala.annotation.tailrec

class Market extends FeatureMultiExtractor {
  val markets = List(1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 20, 21, 22, 23, 24, 26, 27, 28, 33, 34, 35, 37, 39, 43, 45, 47, 93, 96)

  def extract(transactions: List[Transaction], history: History, offer: Offer): List[String] = {
    @tailrec def constr(agg: List[String], leftMarkets: List[Int]): List[String] = {
      leftMarkets match {
        case market :: tail =>
          if (history.market.equals(market.toString)) {
            constr("1" :: agg, tail)
          } else {
            constr("0" :: agg, tail)
          }
        case Nil => agg
      }
    }

    constr(Nil, markets).reverse
  }

  def featureNames(): List[String] = {
    markets.map {
      "market" + _
    }
  }
}