package my.task

import java.time.LocalDate
import org.scalatest.{FlatSpec, Matchers}
import my.task.Common._

class CommonUnit extends FlatSpec with Matchers {
  val array = Array(100.0, 80.0, 60.0, 40.0, 20.0, 5.0)
  val arrayString = Array("1, 2, 3", "4, 5, 6", "7, 8, 9")
  
  "A Common.returnsResult" should "pop the IndexedSeq[Double] of the formula 'first - second / second'" in {
    val result = returnsResult(array, (first: Double, second: Double) => (first - second) / second)
    result should be (IndexedSeq(0.25, 0.3333333333333333, 0.5, 1.0, 3.0))
  }
  
  it should "pop the IndexedSeq[Double] of the formula 'first - second'" in {
    val result = returnsResult(array, (first: Double, second: Double) => first - second)
    result should be (IndexedSeq(20.0, 20.0, 20.0, 20.0, 15.0))
  }
  
  it should "pop values the IndexedSeq[Double] of the formula 'first + second'" in {
    val result = returnsResult(array, (first: Double, second: Double) => first + second)
    result should be (IndexedSeq(180.0, 140.0, 100.0, 60.0, 25.0))
  }
  
  it should "pop values the IndexedSeq[Double] of the formula 'first + second / 2 - second'" in {
    val result = returnsResult(array, (first: Double, second: Double) => (first * second) / 2 - second)
    result should be (IndexedSeq(3920.0, 2340.0, 1160.0, 380.0, 45.0))
  }

  "A Common.stringToDouble" should "pop values 5.5" in {
    stringToDouble("5.5") should be (5.5)
  }
  
  it should "pop values 5" in {
    stringToDouble("5") should be (5)
  }
  
  it should "pop values 0" in {
    stringToDouble("5.w5") should be (0)
  }
  
  "A Common.completeElements" should "pop values Array(5, 8)" in {
    completeElements(getElements(arrayString), "2") should be (Array(5, 8))
  }
  
  it should "pop values Array(4, 7)" in {
    completeElements(getElements(arrayString), "1") should be (Array(4, 7))
  }
  
  it should "pop values Array(6, 9)" in {
    completeElements(getElements(arrayString), "3") should be (Array(6, 9))
  }
  
  it should "pop values Array(-1)" in {
    completeElements(getElements(arrayString), "5") should be (Array(-1))
  }
  
  "A Common.getSource" should "pop values Array('')" in {
    getSource("Some Url to Download") should be (Array(""))
  }
  
  "A Common.getElements" should "pop values Array(Array('1','2','3'), Array('4','5'...)" in {
    getElements(arrayString) should be (Array(Array("1", "2", "3"), Array("4", "5", "6"), Array("7", "8", "9")))
  }
  
  it should "pop values Array(Array('155'))" in {
    getElements(Array("155")) should be (Array(Array("155")))
  }
  it should "pop values Array(Array(''))" in {
    getElements(Array("")) should be (Array(Array("")))
  }
  
  "A Common.pricesURL" should "pop values url" in {
    val date = LocalDate.now()
    val lastYear = date.minusYears(1)
    val ticker = "SomeThing"
    val url = f"http://real-chart.finance.yahoo.com/table.csv?s=$ticker&a=${ lastYear.getMonthValue }&" +
      f"b=${ lastYear.getDayOfMonth }&c=${ lastYear.getYear }&d=${ date.getMonthValue }&" +
      f"e=${ date.getDayOfMonth }&f=${ date.getYear }&g=d&ignore=.csv"
    
    pricesURL(date, ticker) should be (url)
  }
}
