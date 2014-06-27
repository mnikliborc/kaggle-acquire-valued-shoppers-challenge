package shp.preprocessing.features.extraction.extractor

import shp.preprocessing.features.model.transaction.Transaction
import shp.preprocessing.features.model.history.History
import org.joda.time.Period
import shp.preprocessing.features.model.offer.Offer

object Predicates {
  def inLastXDaysBeforeOffer(days: Int)(t: Transaction, h: History, o: Offer): Boolean = {
    (t.date.isEqual(h.offerDate) || (t.date.isBefore(h.offerDate)) &&
      t.date.plus(Period.days(days)).isAfter(h.offerDate))
  }

  def ofOfferBrand(t: Transaction, h: History, o: Offer): Boolean = {
    t.brand.equals(o.brand) && t.company.equals(o.company)
  }

  def ofOfferCategory(t: Transaction, h: History, o: Offer): Boolean = {
    t.category.equals(o.category)
  }

  def ofOfferDept(t: Transaction, h: History, o: Offer): Boolean = {
    t.dept.equals(o.category.take(t.dept.length))
  }

  def ofChain(t: Transaction, h: History, o: Offer): Boolean = {
    t.chain.equals(h.chain)
  }

  def isReturn(t: Transaction, h: History, o: Offer): Boolean = {
    t.purchaseAmount < 0
  }

  def isNotReturn(t: Transaction, h: History, o: Offer): Boolean = {
    t.purchaseAmount > 0
  }
}