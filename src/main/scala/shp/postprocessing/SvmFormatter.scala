package shp.postprocessing

import shp.Files

/**
 * Changes train/test data format from CSV to libSVM.
 */
class SvmFormatter(rawDataLines: Seq[String]) {
  def format(): Seq[String] = {
    rawDataLines.map({
      case vector =>
        val features = vector.split(",")
        val columns = features(0) ++ features.drop(1).zipWithIndex.map({
          case (feature, i) => (i + 1) + ":" + feature
        })
        columns.mkString(" ")
    })
  }
}

object SvmFormatter {
  def main(args: Array[String]): Unit = {
    formatTestData
    formatTrainData
  }

  def formatTestData() = {
    val rawLines = Files.loadLines(Files.testdataRaw)
    val vwLines = new SvmFormatter(rawLines).format()
    Files.save(Files.testdataSvm, vwLines, Option.empty)
  }

  def formatTrainData() = {
    val rawLines = Files.loadLines(Files.traindataRaw)
    val vwLines = new SvmFormatter(rawLines).format
    Files.save(Files.traindataSvm, vwLines, Option.empty)
  }

}