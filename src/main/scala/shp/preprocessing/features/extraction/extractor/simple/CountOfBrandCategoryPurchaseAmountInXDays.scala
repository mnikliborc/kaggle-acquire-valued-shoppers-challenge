package shp.preprocessing.features.extraction.extractor.simple
import shp.preprocessing.features.extraction.extractor.Count
import shp.preprocessing.features.extraction.extractor.Predicates

class CountOfBrandCategoryPurchaseAmountInXDays(val days: Int) extends Count(List(Predicates.inLastXDaysBeforeOffer(days), Predicates.ofOfferBrand, Predicates.ofOfferCategory)) {
  def featureName(): String = {
    s"SumOfBrandCategoryPurchaseAmountIn${days}Days"
  }
}