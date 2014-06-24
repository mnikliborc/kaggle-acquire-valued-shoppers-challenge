package shp.postprocessing

import org.scalatest.Matchers
import org.scalatest.FlatSpec

class TestSetsZipperTest extends FlatSpec with Matchers {
  "Zipper" should "" in {
    val linesA = Seq("a,b,c,d", "1,1,1,1", "3,3,3,3")
    val linesB = Seq("a,e", "2,2", "4,4")
    val zipped = TestSetsZipper.zip(linesA, linesB)

    zipped should have size 3
    zipped should be(Seq("a,b,c,d,e", "1,1,1,1,2", "3,3,3,3,4"))
  }
}