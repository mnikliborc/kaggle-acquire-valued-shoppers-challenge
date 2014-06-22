package shp

import shp.preprocessing.features.extraction.extractor.simple.SumOfBrandPurchaseAmountInXDays
import shp.preprocessing.features.extraction.extractor.simple.SumOfCategoryPurchaseAmountInXDays
import shp.preprocessing.features.extraction.extractor.simple.QuantityOfBrandPurchaseInXDays
import shp.preprocessing.features.extraction.extractor.simple.QuantityOfCategoryPurchaseInXDays
import shp.preprocessing.features.extraction.extractor.simple.CountOfBrandPurchaseInXDays
import shp.preprocessing.features.extraction.extractor.simple.CountOfCategoryPurchaseInXDays
import shp.preprocessing.features.extraction.extractor.simple.CountOfBrandCategoryPurchaseAmountInXDays
import shp.preprocessing.features.extraction.extractor.simple.NeverBoughtBrandCategory
import shp.preprocessing.features.extraction.extractor.simple.NeverBoughtBrand
import shp.preprocessing.features.extraction.extractor.simple.SumOfPurchaseAmount
import shp.preprocessing.features.extraction.extractor.simple.OfferValue
import shp.preprocessing.features.extraction.extractor.FeaturePostExtractor
import shp.preprocessing.features.extraction.extractor.FeatureMultiExtractor
import shp.preprocessing.features.model.FeatureVector
import shp.preprocessing.features.extraction.FeatureVectorsBuilder
import org.joda.time.DateTime

class FeatureExtraction {
  val simpleExtractors = Seq(new SumOfBrandPurchaseAmountInXDays(1000), new SumOfBrandPurchaseAmountInXDays(180), new SumOfBrandPurchaseAmountInXDays(90),
    new SumOfBrandPurchaseAmountInXDays(60), new SumOfBrandPurchaseAmountInXDays(30),

    new SumOfCategoryPurchaseAmountInXDays(1000), new SumOfCategoryPurchaseAmountInXDays(180), new SumOfCategoryPurchaseAmountInXDays(90),
    new SumOfCategoryPurchaseAmountInXDays(60), new SumOfCategoryPurchaseAmountInXDays(30),

    new QuantityOfBrandPurchaseInXDays(180), new QuantityOfBrandPurchaseInXDays(90), new QuantityOfBrandPurchaseInXDays(1000),
    new QuantityOfBrandPurchaseInXDays(60), new QuantityOfBrandPurchaseInXDays(30),

    new QuantityOfCategoryPurchaseInXDays(180), new QuantityOfCategoryPurchaseInXDays(90),
    new QuantityOfCategoryPurchaseInXDays(1000),
    new QuantityOfCategoryPurchaseInXDays(60), new QuantityOfCategoryPurchaseInXDays(30),

    new CountOfBrandPurchaseInXDays(1000),
    new CountOfBrandPurchaseInXDays(180), new CountOfBrandPurchaseInXDays(90), new CountOfBrandPurchaseInXDays(60),
    new CountOfBrandPurchaseInXDays(30),

    new CountOfCategoryPurchaseInXDays(1000),
    new CountOfCategoryPurchaseInXDays(180), new CountOfCategoryPurchaseInXDays(90),
    new CountOfCategoryPurchaseInXDays(60), new CountOfCategoryPurchaseInXDays(30),

    new CountOfBrandCategoryPurchaseAmountInXDays(1000), new NeverBoughtBrand,
    new NeverBoughtBrandCategory,

    new OfferValue, new SumOfPurchaseAmount)
  val postExtractors: Seq[FeaturePostExtractor] = Seq()
  val multiExtractors: Seq[FeatureMultiExtractor] = Seq()

  def extract(dataSetType: DataSetType): Iterable[FeatureVector] = {
    val builder = new FeatureVectorsBuilder(dataSetType, simpleExtractors, postExtractors, multiExtractors)
    builder.build
  }

}

object FeatureExtraction {
  TimeMeter.init

  val fe = new FeatureExtraction

  def main(args: Array[String]): Unit = {
    val tm = TimeMeter.start("")

    extractTrain
    extractTest

    tm.stop
  }

  def extractTrain = {
    println("start=" + new DateTime())

    val vectors = fe.extract(DataSetType.TRAIN)
    saveVectors(Files.traindataRaw, vectors)

    println("end=" + new DateTime())
  }

  def extractTest = {
    println("start=" + new DateTime())

    val vectors = fe.extract(DataSetType.TEST)
    saveVectors(Files.testdataRaw, vectors)
    saveIds(Files.testIds, vectors)

    println("end=" + new DateTime())
  }

  def saveVectors(filename: String, vectors: Iterable[FeatureVector]) = {
    val allFeatureNames = (fe.simpleExtractors.map(_.featureName) ++ fe.postExtractors.map(_.featureName) ++ fe.multiExtractors.flatMap(_.featureNames));
    Files.save(filename, vectors.map(_.toString), Option("repeater," + allFeatureNames.mkString(",")))
  }

  def saveIds(filename: String, vectors: Iterable[FeatureVector]) = {
    Files.save(filename, vectors.map(_.history.id), Option.empty)
  }

}