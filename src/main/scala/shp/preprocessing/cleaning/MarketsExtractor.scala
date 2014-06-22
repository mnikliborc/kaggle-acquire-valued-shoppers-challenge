package shp.preprocessing.cleaning

import shp.preprocessing.features.model.history.HistoryStore
import shp.preprocessing.features.model.history.History
import scala.collection.mutable.HashMap

object MarketsExtractor {

  def main(args: Array[String]) = {
    val trainStore = new HistoryStore("trainHistory.csv", History.ofTrain)
    val testStore = new HistoryStore("testHistory.csv", History.ofTest)

    plotHistogram(trainStore, "train")
    plotHistogram(testStore, "test")
  }

  def plotHistogram(store: HistoryStore, label: String) {
    val hist = new HashMap[String, Int]
    store.items.values.foreach {
      h =>
        val count = hist.getOrElse(h.market, 0)
        hist.put(h.market, count + 1)
    }

    println(label)
    hist.keys.map(_.toInt).toList.sorted(Ordering.Int) foreach {
      case market =>
        val count = hist.getOrElse(market.toString, 0)
        println(s"$market,$count")
    }
    println
  }
}