package shp.postprocessing

import shp.Files

/**
 * Zips two feature vectors files. Omits 'repeater' feature in the second file.
 */
object TestSetsZipper {
  def main(args: Array[String]) = {
    Files.save("data/3/testdata.csv", zip(Files.loadLines("data/2/testdata.csv"), Files.loadLines("data/testdata.csv")), None)
    Files.save("data/3/traindata.csv", zip(Files.loadLines("data/2/traindata.csv"), Files.loadLines("data/traindata.csv")), None)
  }

  def zip(linesA: Seq[String], linesB: Seq[String]): List[String] = {
    val headerA = linesA.head
    val headerBWithoutRepeater = linesB.head.split(",").drop(1).mkString(",")
    val newHeader = headerA + "," + headerBWithoutRepeater

    newHeader :: linesA.drop(1).zip(linesB.drop(1)).map({
      case (lineA, lineB) =>
        val lineBWithoutRepeater = lineB.split(",").drop(1).mkString(",")
        lineA + "," + lineBWithoutRepeater
    }).toList
  }
}