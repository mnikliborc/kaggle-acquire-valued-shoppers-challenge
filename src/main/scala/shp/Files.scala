package shp

import java.io.PrintWriter
import java.io.File
import scala.io.Source

object Files {
  val traindataRaw = "data/traindata.csv"
  val testdataRaw = "data/testdata.csv"

  val traindataVw = "data/traindata.vw"
  val testdataVw = "data/testdata.vw"
  val traindataSvm = "data/traindata.svm"
  val testdataSvm = "data/testdata.svm"

  val testIds = "data/testids.csv"

  val predictionKaggle = "data/prediction-kaggle.csv"
  val predictionVw = "data/prediction.vw"

  val trainValidationVw = "data/train-valid-vw.csv"
  val testValidationVw = "data/test-valid-vw.csv"
  val testValidationTargetsVw = "data/test-valid-targets-vw.csv"
  val trainValidationTargetsVw = "data/train-valid-targets-vw.csv"

  val allTransactions = "data/transactions.csv"
  val trainTransactions = "data/train-transactions.csv"
  val trainTransactionsCategoryBrand = "data/train-transactions-cat-brand.csv"
  val testTransactions = "data/test-transactions.csv"
  val testTransactionsCategoryBrand = "data/test-transactions-cat-brand.csv"

  val solution = "data/solution.csv"

  val trainHistory = "data/trainHistory"
  val testHistory = "data/testHistory"

  def save(outputPath: String, lines: Iterable[String], headerOpt: Option[String]) = {
    val writer = new PrintWriter(new File(outputPath))
    (headerOpt ++ lines).foreach(l => writer.write(l + "\n"))
    writer.close
  }

  def loadLines(inputPath: String) = {
    Source.fromFile(inputPath).getLines.toSeq
  }

  def loadLazy(inputPath: String) = {
    Source.fromFile(inputPath).getLines
  }
}