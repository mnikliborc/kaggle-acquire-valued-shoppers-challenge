package shp.preprocessing.cleaning

import shp.preprocessing.features.model.transaction.TransactionLazyStore
import shp.Files

/**
 * Counts how many transactions have purchaseAmount < 0 (products were returned)
 *
 * Result:
 * train: size=187105085, returns=4119635
 * test: size=162550704, returns=3995367
 */
object ReturnsCounter {
  def main(args: Array[String]) {
    val trainTrans = new TransactionLazyStore(Files.trainTransactions)
    val testTrans = new TransactionLazyStore(Files.testTransactions)

    count(trainTrans)
    count(testTrans)
  }

  def count(trans: TransactionLazyStore) = {
    var size = 0
    var returns = 0
    trans.items.foreach {
      t =>
        if (t.purchaseAmount < 0) {
          returns += 1
        }
        size += 1
    }
    println(s"size=$size, returns=$returns")
  }
}