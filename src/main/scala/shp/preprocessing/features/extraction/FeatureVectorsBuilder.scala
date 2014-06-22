package shp.preprocessing.features.extraction

import shp.DataSetType
import shp.preprocessing.features.model.FeatureVector
import shp.preprocessing.features.model.history.HistoryStore
import shp.preprocessing.features.model.offer.OfferStore
import scala.collection.parallel.ForkJoinTaskSupport
import shp.preprocessing.features.model.offer.Offer
import scala.parallel.Future
import scala.collection.parallel.TaskSupport
import shp.preprocessing.features.model.transaction.CustomersTransactions
import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.model.history.History
import shp.preprocessing.features.extraction.extractor.FeatureSimpleExtractor
import shp.preprocessing.features.extraction.extractor.FeatureMultiExtractor
import shp.preprocessing.features.extraction.extractor.FeaturePostExtractor
import scala.collection.parallel.ParIterable
import scala.collection.immutable

class FeatureVectorsBuilder(dataSetType: DataSetType, simpleExtractors: Seq[FeatureSimpleExtractor], postExtractors: Seq[FeaturePostExtractor], multiExtractors: Seq[FeatureMultiExtractor]) {
  val historyStore = new HistoryStore(dataSetType.historyFilename, dataSetType.historyParser)
  val offerStore = new OfferStore

  val customerTransactions = new CustomersTransactions(dataSetType.transactionsFilename)
  val featureNames = simpleExtractors.map(_.featureName) ++ postExtractors.map(_.featureName) ++ multiExtractors.flatMap(_.featureNames)

  def build(): Iterable[FeatureVector] = {
    val vectors = customerTransactions.map {
      transactions => buildVector(transactions, transactions.head.id)
    }

    val missingVectors = createMissingVectors(vectors)
    vectors ++ missingVectors
  }

  private def createMissingVectors(vectors: Iterable[shp.preprocessing.features.model.FeatureVector]): immutable.Set[shp.preprocessing.features.model.FeatureVector] = {
    val missingIds = historyStore.items.keys.toSet -- vectors.map(_.history.id)
    val missingVectors = missingIds.map {
      id =>
        buildVector(Nil, id)
    }

    missingVectors
  }

  private def buildVector(transactions: List[Transaction], historyId: String): FeatureVector = {
    val history = historyStore.items.getOrElse(historyId, throw new RuntimeException)
    val offer = offerStore.items.getOrElse(history.offer, throw new RuntimeException)

    val featureValues = simpleExtractors.map(_.extract(transactions, history, offer))
    val featureMultiValues = multiExtractors.flatMap(_.extract(transactions, history, offer))
    val featurePostValues = postExtractors.map(_.extract(featureNames.zip(featureValues).toMap, history, offer))

    val vector = new FeatureVector(history, offer)
    vector.fillFeatures(featureNames, featureValues ++ featurePostValues ++ featureMultiValues)
    vector
  }
}