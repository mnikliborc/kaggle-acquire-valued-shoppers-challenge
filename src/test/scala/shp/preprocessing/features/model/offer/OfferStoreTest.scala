package shp.preprocessing.features.model.offer

import org.scalatest.FlatSpec
import org.scalatest.Matchers
/**
 * offer,category,quantity,company,offervalue,brand
 * 1190530,9115,1,108500080,5,93904
 * 1194044,9909,1,107127979,1,6732
 * 1197502,3203,1,106414464,0.75,13474
 */
class OfferStoreTest extends FlatSpec with Matchers {
  "Offer store" should "load and parse data" in {
    val store = new OfferStore
    val offerOpt = store.items.get("1194044")

    store.items should have size (37)

    offerOpt should not be ('empty)
    offerOpt.get.category should be("9909")
    offerOpt.get.quantity should be(1)
    offerOpt.get.company should be("107127979")
    offerOpt.get.offervalue should be(1)
    offerOpt.get.brand should be("6732")
  }

}