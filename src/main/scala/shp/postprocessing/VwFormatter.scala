package shp.postprocessing

import scala.io.Source

import shp.Files

/**
 * Changes train/test data format from CSV to Vowpal Wabbit.
 */
class VwFormatter(rawDataLines: Seq[String]) {
  def format(): Seq[String] = {
    rawDataLines.map({
      case vector =>
        val features = vector.split(",")
        val columns = features(0) + " |" ++ features.drop(1).zipWithIndex.map({
          case (feature, i) => (i + 1) + ":" + feature
        })
        columns.mkString(" ")
    })
  }
}

object VwFormatter {
  def main(args: Array[String]): Unit = {
    formatTestData
    formatTrainData
  }

  def formatTestData() = {
    val rawLines = Files.loadLines(Files.testdataRaw)
    val vwLines = new VwFormatter(rawLines).format()
    Files.save(Files.testdataVw, vwLines, Option.empty)
  }

  def formatTrainData() = {
    val rawLines = Files.loadLines(Files.traindataRaw)
    val vwLines = new VwFormatter(rawLines).format
    Files.save(Files.traindataVw, vwLines, Option.empty)
  }

}