package shp.preprocessing.features.extraction

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import scala.collection.mutable.HashSet
import shp.preprocessing.features.model.transaction.CustomersTransactions
import shp.postprocessing.TestData

class CustomerTransactionStreamTest extends FlatSpec with Matchers with TestData {
  "CustomerTransactionStream" should " return true on hasNext()" in {
    val stream = new CustomersTransactions(manyTransactions)
    stream.iterator.hasNext should be(true)
  }

  "CustomerTransactionStream" should " return transactions of one customer on next()" in {
    val stream = new CustomersTransactions(manyTransactions)
    val transactions = stream.iterator.next

    def allElementsTheSame(el: String, list: List[String]): Boolean = {
      list match {
        case head :: tail =>
          if (head != el) false
          else allElementsTheSame(el, tail)
        case Nil => true
      }
    }

    val ids = transactions.map(_.id)
    allElementsTheSame(ids.head, ids) should be(true)
  }

  "CustomerTransactionStream" should " return transactions of one customer on first next() and of another on second next()" in {
    val stream = new CustomersTransactions(manyTransactions)
    val customer1transactions = stream.iterator.next
    val customer2transactions = stream.iterator.next

    customer1transactions.head.id should not be (customer2transactions.head.id)
  }

  "CustomerTransactionStream" should " have unique id values" in {
    val stream = new CustomersTransactions(manyTransactions)
    var set = new HashSet[String]

    for (trans <- stream) {
      val id = trans.head.id
      if (set.contains(id)) {
        println(id)
      } else {
        set.add(id)
      }
    }
  }
}