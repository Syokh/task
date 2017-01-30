package my.task

import my.task.Common._

object MainApp extends App {
  /**
    * for example either Open or High or Low or Close or Volume or Adj Close
    */
  val nameColumn = "Adj Close"
  val ticker = "GOOG"

  val googleDailyPrices = dailyPrices(ticker, nameColumn)
  val googleDailyReturns = returns(ticker, nameColumn)
  val googleAverageReturns = meanReturn(ticker, nameColumn)
  
  println(s"googleDailyPrices = $googleDailyPrices \n\ngoogleDailyReturns = $googleDailyReturns\n\n" +
    s"googleAverageReturns = $googleAverageReturns")

  /* 1 - 1 year historic prices given a ticker */
  def dailyPrices(ticker: String, nameColumn: String): List[Double] = {
    completeElements(downloadData(ticker), nameColumn).toList
  }
  
  /* 2- daily returns, where return = ( Price_Today – Price_Yesterday)/Price_Yesterday */
  def returns(ticker: String, nameColumn: String): Seq[Double] = {
    val array = completeElements(downloadData(ticker), nameColumn)
    returnsResult(array, (today: Double, yesterday: Double) => (today - yesterday) / yesterday)
  }
  
  /* 3 – 1 year mean returns */
  def meanReturn(ticker: String, nameColumn: String): Double = {
    val result = completeElements(downloadData(ticker), nameColumn)
    result.sum / result.length
  }
}
