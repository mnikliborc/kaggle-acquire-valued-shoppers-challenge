package shp.postprocessing

import java.io.PrintWriter
import scala.io.Source
import java.io.File
import shp.Files

/**
 * Zips file of history.ids with predictions in order to get file in kaggle submission format.
 */
object IdAndPredictionZipper {
  def main(args: Array[String]) = {
    val predictionFilename = Files.predictionVw
    Files.save(Files.solution, zipWithTestIds(predictionFilename), Option("id,repeatProbability"))
  }

  def zipWithTestIds(predictionFilename: String): List[String] = {
    Files.loadLines(predictionFilename).zip(Files.loadLines(Files.testIds)).map({
      case (predictionLine, testId) =>
        testId + "," + predictionLine
    }).toList
  }
}