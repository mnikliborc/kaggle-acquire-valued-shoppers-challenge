package shp.preprocessing.cleaning

import shp.preprocessing.features.model.transaction.TransactionLazyStore
import shp.preprocessing.features.model.offer.OfferStore
import scala.collection.mutable.HashMap
import shp.DataSetType
import shp.preprocessing.features.model.offer.Offer
import scala.collection.mutable.HashSet
import java.io.PrintWriter
import java.io.File

object DepartmentCategoryRelationEstimator {
  val trainTrans = new TransactionLazyStore(DataSetType.TRAIN.transactionsFilename)
  val testTrans = new TransactionLazyStore(DataSetType.TEST.transactionsFilename)
  val offerStore = new OfferStore

  val deptCatMap = new HashMap[String, HashSet[String]]

  def main(args: Array[String]) = {
    goOverTransactions(trainTrans)
    goOverTransactions(testTrans)

    val writer = new PrintWriter(new File("deptCatRelation.csv"))
    writer.write("dept,category")
    deptCatMap.foreach {
      case (dept, cats) =>
        cats.foreach {
          case cat => writer.write(s"$dept,$cat\n")
        }
    }
    writer.close
  }

  def goOverTransactions(trans: TransactionLazyStore) {
    for (t <- trans.items) {
      val set = deptCatMap.getOrElse(t.dept, new HashSet[String])
      set.add(t.category)
      deptCatMap.put(t.dept, set)
    }
  }
}