package shp.preprocessing.features.extraction

import shp.Files

/**
 * Zips two files with feature vectors. Use to extend the feature space dim.
 */
object FeatureVectorsFileZipper {
  def zipAndSave(inputFile1: String, inputFile2: String, outputFile: String) = {
    Files.save(outputFile, zip(inputFile1, inputFile2), Option.empty)
  }

  def zip(inputFile1: String, inputFile2: String): Seq[String] = {
    Files.loadLines(inputFile1).zip(Files.loadLines(inputFile1)).map {
      case (vect1, vect2) => vect1 + "," + vect2;
    }
  }
}