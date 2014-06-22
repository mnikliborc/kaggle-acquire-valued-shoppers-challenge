package shp.preprocessing.cleaning

import shp.preprocessing.features.model.transaction.TransactionLazyStore
import shp.preprocessing.features.model.offer.OfferStore
import scala.collection.mutable.HashMap
import shp.DataSetType
import shp.preprocessing.features.model.offer.Offer

object PriceOfOfferedCategoryEstimator {
  val trainTrans = new TransactionLazyStore(DataSetType.TRAIN.transactionsFilename)
  val testTrans = new TransactionLazyStore(DataSetType.TEST.transactionsFilename)
  val offerStore = new OfferStore

  val offerMap = new HashMap[String, Offer]
  offerStore.items.values.foreach {
    case o => offerMap.put(o.category, o)
  }

  val priceSumMap = new HashMap[String, Double]
  val priceCountMap = new HashMap[String, Int]

  def main(args: Array[String]) = {
    sumOverTransactions(trainTrans)
    sumOverTransactions(testTrans)

    priceSumMap.foreach {
      case (offer, sum) =>
        val count = priceCountMap.getOrElse(offer, 1)
        val avgPrice = sum / count
        println(s"$offer,$avgPrice,$count")
    }
  }

  def sumOverTransactions(trans: TransactionLazyStore) {
    for (t <- trans.items) {
      val offerOpt = offerMap.get(t.category)
      offerOpt foreach {
        case Offer(offer, _, _, _, _, _) =>
          val quantity = if (t.purchaseQuantity == 0) 1 else t.purchaseQuantity
          val size = if (t.productSize == 0) 1 else t.productSize
          val pricePerUnit = t.purchaseAmount / (quantity * size)

          val sum: Double = priceSumMap.getOrElse(offer, 0)
          priceSumMap.put(offer, sum + pricePerUnit)

          val count: Int = priceCountMap.getOrElse(offer, 0)
          priceCountMap.put(offer, count + 1)
      }
    }
  }
}