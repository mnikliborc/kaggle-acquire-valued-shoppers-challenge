package shp.preprocessing.features.model.transaction

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import shp.postprocessing.TestData
import shp.Files

/**
 * 86246,205,7,707,1078778070,12564,2012-03-02,12,OZ,1,7.59
 * 86246,205,63,6319,107654575,17876,2012-03-02,64,OZ,1,1.59
 */
class TransactionStreamTest extends FlatSpec with Matchers with TestData {

  "Transaction stream" should "load and parse data" in {
    val store = new TransactionLazyStore(manyTransactions)
    val transaction = store.items.next()

    transaction.id should be("86246")
    transaction.chain should be("205")
    transaction.dept should be("7")
    transaction.category should be("707")
    transaction.company should be("1078778070")
    transaction.brand should be("12564")
    transaction.date.toString("yyyy-MM-dd") should be("2012-03-02")
    transaction.productSize should be(12)
    transaction.productMeasure should be("OZ")
    transaction.purchaseQuantity should be(1)
    transaction.purchaseAmount should be(7.59)
  }

  //  "Transaction stream" should "be sorted by id" in {
  //    val store = new TransactionLazyStore(Files.allTransactions)
  //    var prev = store.items.next
  //
  //    for (t <- store.items) {
  //      if (t.id.length() < prev.id.length || (t.id.length() == prev.id.length && t.id.compare(prev.id) < 0)) {
  //        println(prev.id)
  //        println(t.id)
  //      }
  //      prev = t
  //    }
  //  }

}