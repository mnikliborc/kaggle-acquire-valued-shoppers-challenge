package shp.preprocessing.features.model.offer

import scala.collection.mutable.HashMap
import shp.Files

class OfferStore {
  val lines = Files.loadLines("data/offers.csv").drop(1)
  val items = new HashMap[String, Offer]

  lines.foreach {
    line =>
      val o = Offer.of(line)
      items.put(o.offer, o)
  }
}