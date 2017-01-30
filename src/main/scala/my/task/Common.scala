package my.task

import java.time.LocalDate
import scala.io.Source
import scala.util.{Failure, Success, Try}

object Common {
  def downloadData(ticker: String): Array[Array[String]] = {
    val url = pricesURL(LocalDate.now(), ticker)
    getElements(getSource(url))
  }
  
  def completeElements(elements: Array[Array[String]], nameColumn: String): Array[Double] ={
    val index = elements(0).indexOf(nameColumn)
    if (index != -1) elements.drop(1).map(_ (index)).map(stringToDouble)
    else Array(-1)
  }
  
  def pricesURL(businessDate: LocalDate, ticker: String): String = {
    val lastYear = businessDate.minusYears(1)
    f"http://real-chart.finance.yahoo.com/table.csv?s=$ticker&a=${ lastYear.getMonthValue }&" +
      f"b=${ lastYear.getDayOfMonth }&c=${ lastYear.getYear }&d=${ businessDate.getMonthValue }&" +
      f"e=${ businessDate.getDayOfMonth }&f=${ businessDate.getYear }&g=d&ignore=.csv"
  }
  
  def returnsResult[A](list: Array[A], function: (A, A) => A): IndexedSeq[A] = {
    for (i <- 1 until list.length) yield function(list(i - 1), list(i))
  }
  
  def getElements(elements: Array[String]): Array[Array[String]] = elements.map(_.split(",").map(_.trim))
  
  def getSource(url: String): Array[String] =
    Try(Source.fromURL(url).getLines.toArray) match {
      case Success(l) => l
      case Failure(_) => Array("")
    }
  
  def stringToDouble(st: String): Double = Try(st.toDouble) match {
    case Success(l) => l
    case Failure(_) => 0
  }
}
