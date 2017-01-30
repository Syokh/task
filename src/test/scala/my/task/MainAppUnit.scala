package my.task

import org.scalatest.{FlatSpec, Matchers}
import my.task.MainApp._

class MainAppUnit extends FlatSpec with Matchers {
  val tickers = "GOOG"

  val resultPrices = dailyPrices(tickers, "Open")
  val resultReturns = returns(tickers, "Low")
  val resultMeanReturn = meanReturn(tickers, "High")
  
  "A MainApp.dailyPrices" should "pop List[Double]" in {
    resultPrices.size should be > 0
  }
  
  "A MainApp.dailyPrices" should "pop Seq[Double]" in {
    resultReturns.size should be > 0
  }
  
  "A MainApp.dailyPrices" should "pop Double" in {
    resultMeanReturn should be > 0.0
  }
}
