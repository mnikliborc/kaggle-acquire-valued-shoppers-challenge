package shp.preprocessing.cleaning

import shp.preprocessing.features.model.transaction.TransactionLazyStore
import shp.Files
import scala.collection.mutable
import scala.collection.DefaultMap
import shp.preprocessing.features.model.transaction.Transaction

object EstimateAvgPricesPerUnit {
  case class Product(company: String, brand: String, category: String, productMeasure: String)

  def main(args: Array[String]) {
    val trainTrans = new TransactionLazyStore(Files.trainTransactions)
    val testTrans = new TransactionLazyStore(Files.testTransactions)

    val trainAvgPrices = estimate(trainTrans, "data/avg-train-prices.csv")
    val testAvgPrices = estimate(testTrans, "data/avg-test-prices.csv")

    calculateRatio(trainAvgPrices, testAvgPrices)
  }

  def calculateRatio(trainAvgPrices: mutable.Map[Product, (BigDecimal, Int)], testAvgPrices: mutable.Map[Product, (BigDecimal, Int)]) {
    val avgPriceRatios = trainAvgPrices.keys.flatMap {
      prod =>
        if (testAvgPrices.get(prod).isDefined) {
          val (trainSum, trainCount) = trainAvgPrices.get(prod).get
          val (testSum, testCount) = testAvgPrices.get(prod).get

          val trainAvg = (trainSum / trainCount)
          val testAvg = (testSum / testCount)

          Some(List(prod.company, prod.brand, prod.category, prod.productMeasure, trainAvg / testAvg).mkString(","))
        } else {
          None
        }
    }
    Files.save("data/avg-train-test-prices-ratio.csv", avgPriceRatios, Option("company,brand,category,productMeasure,avgPriceRatio"))
  }

  def estimate(trans: TransactionLazyStore, output: String) = {
    val avgPrices: mutable.Map[Product, (BigDecimal, Int)] = mutable.Map.empty

    trans.items.foreach {
      t =>
        if (t.purchaseAmount > 0) {
          val p = toProduct(t)
          val pricePerUnit = toPricePerUnit(t)
          val (sum, count): (BigDecimal, Int) = avgPrices.getOrElse(p, (0, 0))
          avgPrices.put(p, (sum + pricePerUnit, count + 1))
        }
    }
    val lines = avgPrices.toStream.map {
      case (prod, (sum, count)) => List(prod.company, prod.brand, prod.category, prod.productMeasure, sum / count).mkString(",")
    }

    Files.save(output, lines, Option("company,brand,category,productMeasure,avgPrice"))
    avgPrices
  }

  def toProduct(t: Transaction): Product = Product(t.company, t.brand, t.category, t.productMeasure)
  def toPricePerUnit(t: Transaction): Double = t.purchaseAmount / (zeroToOne(t.purchaseQuantity) * zeroToOne(t.productSize))
  def zeroToOne(v: Double) = if (v == 0) 1 else v
}