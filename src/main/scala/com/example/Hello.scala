package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    val D = readDataSet
    
    D.foreach{d =>
      println(s"${d._1}")
      d._2.map(x => "  " + x.mkString(", "))
        .foreach(println)
    }
  }

  def readDataSet = {
    var D = List[(String, List[List[String]])]()
    val bufferedSource = io.Source.fromFile("./data-2016.csv")
    for (line <- bufferedSource.getLines) {
      val cols = line.split(" ").map(_.trim).toList

      val registration_year = cols.head

      val registration_set = cols.tail.sliding(5).toList

      D = (registration_year, registration_set) :: D
    }
    bufferedSource.close

    D
  }
}
