/**
  * Base class for classes to read data sets.
  * @tparam T the type of transactions
  */
abstract class DataSetReader[T] {

  /**
    * Reads a data set.
    * @param path The csv file to read. One line -> one transaction
    * @return the data set
    */
  def readDataSet(path: String): List[T]= {
    var D = List[T]()
    val bufferedSource = io.Source.fromFile("./data.csv")
    for (line <- bufferedSource.getLines) {
      val entry = convertLine(line)
      D = entry :: D
    }
    bufferedSource.close

    D
  }

  /**
    * Converts one line in the csv to a transaction
    * @param line the line to be converted
    * @return a converted entry
    */
  def convertLine(line: String): T
}
