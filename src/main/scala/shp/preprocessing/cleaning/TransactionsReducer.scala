package shp.preprocessing.cleaning

import shp.Files
import java.io.PrintWriter
import java.io.File
import shp.preprocessing.features.model.offer.OfferStore

object TransactionsReducer {
  val offers = new OfferStore
  val offersCat = offers.items.values.map(_.category).toSet
  val offersComp = offers.items.values.map(_.company).toSet

  def main(args: Array[String]): Unit = {
    reduce(Files.trainTransactions, Files.trainTransactionsCategoryBrand)
    reduce(Files.testTransactions, Files.testTransactionsCategoryBrand)
  }

  def reduce(input: String, output: String) = {
    val writer = new PrintWriter(new File(output))
    Files.loadLazy(input).foreach {
      case line =>
        val split = line.split(",")
        if (offersCat.contains(split(3)) || offersComp.contains(split(4))) {
          writer.write(line + "\n")
        }
    }
    writer.close
  }
}