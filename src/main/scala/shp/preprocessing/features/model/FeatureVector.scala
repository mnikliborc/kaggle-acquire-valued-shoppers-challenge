package shp.preprocessing.features.model

import scala.collection.mutable
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.model.offer.Offer

class FeatureVector(val history: History, val offer: Offer) {
  val featureValues = mutable.Map[String, String]()
  var featureNames: Seq[String] = Nil

  def fillFeatures(featureNames: Seq[String], values: Seq[String]) = {
    this.featureNames = featureNames
    featureNames.zip(values).foreach(x => featureValues.put(x._1, x._2))
  }

  override def toString(): String = {
    val features = featureNames.map(featureValues.getOrElse(_, throw new RuntimeException)).mkString(",")
    val repeater = if (history.repeater) "1" else "0"

    s"$repeater,$features"
  }
}
