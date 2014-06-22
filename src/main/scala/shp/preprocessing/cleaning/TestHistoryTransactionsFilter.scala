package shp.preprocessing.cleaning

import scala.io.Source
import scala.collection.mutable.HashSet
import java.io.PrintWriter
import java.io.File
import shp.TimeMeter

/**
 * Takes tansactions.csv file and leave out rows without mapping to testHistory.csv.
 */
class HistoryTransactionsFilter {
  TimeMeter.init

  def filter() = {
    val idChainHistorySet = getIdChainHistorySet

    val tm = TimeMeter.start("")
    var counter: Long = 0
    var allCounter: Long = 0
    val writer = new PrintWriter(new File("/Users/marcin/projects/kaggle/test-transactions-filtered.csv"))
    for (line <- Source.fromFile("/Users/marcin/projects/kaggle/transactions.csv").getLines().drop(1)) {
      if (idChainHistorySet.contains(extractIdAndChain(line))) {
        counter += 1
        writer.write(line)
        writer.write("\n")
      }
      allCounter += 1
    }
    writer.close()
    tm.stop
    println(counter)
    println(allCounter)
  }

  def getIdChainHistorySet: HashSet[(String, String)] = {
    val idChainHistorySet = new HashSet[(String, String)]
    for (line <- read("testHistory.csv").drop(1)) {
      idChainHistorySet.add(extractIdAndChain(line))
    }
    idChainHistorySet
  }

  def read(filename: String): Iterator[String] = {
    Source.fromFile("src/main/resources/" + filename).getLines()
  }

  private def extractIdAndChain(line: String): (String, String) = {
    val attrVals = line.split(",")
    (attrVals(0), attrVals(1))
  }
}

object HistoryTransactionsFilter {
  def main(args: Array[String]) = {
    val filter = new HistoryTransactionsFilter
    //filter.filter
  }
}