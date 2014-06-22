package shp.preprocessing.features.model.history

import scala.collection.mutable.HashMap
import shp.Files

class HistoryStore(filename: String, parser: (String => History)) {
  val lines = Files.loadLines(filename).drop(1)
  val items = new HashMap[String, History]

  lines.foreach {
    line =>
      val h = parser(line)
      items.put(h.id, h)
  }
}