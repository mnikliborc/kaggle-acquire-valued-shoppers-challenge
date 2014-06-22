package shp.preprocessing.features.model.history

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import shp.postprocessing.TestData
import shp.Files
/**
 * id,chain,offer,market,repeattrips,repeater,offerdate
 * 86246,205,1208251,34,5,t,2013-04-24
 * 86252,205,1197502,34,16,t,2013-03-27
 * 12682470,18,1197502,11,0,f,2013-03-28
 *
 * id,chain,offer,market,offerdate
 * 12262064,95,1219903,39,2013-06-27
 * 12277270,95,1221658,39,2013-06-23
 * 12332190,95,1213242,39,2013-06-15
 */
class HistoryStoreTest extends FlatSpec with Matchers {
  "History store" should "load and parse TRAIN data" in {
    val store = new HistoryStore(Files.trainHistory, History.ofTrain)
    val historyOpt = store.items.get("86252")

    historyOpt should not be ('empty)
    historyOpt.get.chain should be("205")
    historyOpt.get.offer should be("1197502")
    historyOpt.get.market should be("34")
    historyOpt.get.repeater should be(true)
    historyOpt.get.offerDate.toString("yyyy-MM-dd") should be("2013-03-27")

    store.items.size should be(160057)
  }

  "History store" should "load and parse TEST data" in {
    val store = new HistoryStore(Files.testHistory, History.ofTest)
    val historyOpt = store.items.get("12277270")

    historyOpt should not be ('empty)
    historyOpt.get.chain should be("95")
    historyOpt.get.offer should be("1221658")
    historyOpt.get.market should be("39")
    historyOpt.get.repeater should be(false)
    historyOpt.get.offerDate.toString("yyyy-MM-dd") should be("2013-06-23")

    store.items.size should be(151484)
  }

  "History id" should "be unique" in {
    val store = new HistoryStore(Files.testHistory, History.ofTest)
    store.items.values.toSet.size should be(store.items.values.size)
  }

}