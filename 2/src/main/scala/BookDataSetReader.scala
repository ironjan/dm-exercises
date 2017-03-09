/**
  * [[DataSetReader]] for individual assignment 2 with Book Data
  */
class BookDataSetReader extends DataSetReader[Set[String]]{

  override def convertLine(line: String) = {
    line.split(" ").map(_.trim).toSet
  }
}

