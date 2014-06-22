package shp.preprocessing.cleaning

import scala.collection.mutable.HashSet
import shp.Files
import shp.TimeMeter
import java.io.PrintWriter
import java.io.File
import scala.io.Source

/**
 * Takes tansactions.csv file and splits into train-transactions.csv/test-transactions.csv files depending whether
 * transaction.id is in trainHistory.csv or testHistory.csv.
 */
object TransactionsSplitter {
  def main(args: Array[String]) = {
    split("data/testHistory", "data/trainHistory")
  }

  def split(testHistoryFile: String, trainHistoryFile: String) = {
    val testHistoryIds = getHistoryIds(testHistoryFile)
    val trainHistoryIds = getHistoryIds(trainHistoryFile)

    val testWriter = new PrintWriter(Files.testTransactions)
    val trainWriter = new PrintWriter(Files.trainTransactions)
    for (line <- Source.fromFile(Files.allTransactions).getLines().drop(1)) {
      val historyId = extractId(line)

      if (testHistoryIds.contains(historyId)) {
        testWriter.write(line + "\n")
      } else if (trainHistoryIds.contains(historyId)) {
        trainWriter.write(line + "\n")
      } else {
        println(String.format("%s is missing in *history file.", historyId))
      }
    }
    testWriter.close()
    trainWriter.close()
  }

  def extractId(line: String): String = line.split(",")(0)

  def getHistoryIds(historyFile: String): HashSet[String] = {
    val historyIds = new HashSet[String]
    for (line <- Files.loadLines(historyFile).drop(1)) {
      historyIds.add(extractId(line))
    }
    historyIds
  }

}