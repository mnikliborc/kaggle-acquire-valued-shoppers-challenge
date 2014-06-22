package shp.preprocessing.features.model.transaction

/**
 * Loads transactions stream and returns them in batches - one batch for one customer (assuming TransactionStream is ordered by customer)
 */
class CustomersTransactions(filename: String) extends Iterable[List[Transaction]] {
  val transStore = new TransactionLazyStore(filename)

  def iterator = new Iterator[List[Transaction]] {
    override def hasNext() = transStore.items.hasNext
    override def next(): List[Transaction] = {
      if (transStore.items.hasNext) {
        val first = transStore.items.next
        first :: transStore.items.takeWhile(first.id == _.id).toList
      } else {
        if (transStore.items.hasNext) throw new RuntimeException
        else Nil
      }
    }
  }
}