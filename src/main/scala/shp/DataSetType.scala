package shp

import shp.preprocessing.features.model.history.History

class DataSetType(val transactionsFilename: String, val historyFilename: String, val historyParser: (String) => (History))

object DataSetType {
  val TEST = new DataSetType(Files.testTransactions, "data/testHistory", History.ofTest)
  val TRAIN = new DataSetType(Files.trainTransactions, "data/trainHistory", History.ofTrain)
}