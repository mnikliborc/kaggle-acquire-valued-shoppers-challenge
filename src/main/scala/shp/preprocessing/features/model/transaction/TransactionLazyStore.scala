package shp.preprocessing.features.model.transaction

import shp.Files

class TransactionLazyStore(filename: String) {
  val lines = Files.loadLazy(filename)
  val items = lines.map(Transaction.of(_))
}