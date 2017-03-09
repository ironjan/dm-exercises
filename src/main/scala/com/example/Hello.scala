package com.example

/**
  * 1. Write an application that reads in the dataset, and prints out all the different course_codes in the file.
  * 2. Create a program (or a method) that counts the number of students that have attempted the course "Introduction to programming"
  * 3. Create a program (or a method) that counts the number of students that have passed the course "Introduction to programming"
  * 4. Create a program (or a method) that counts the number of students that have passed the course "Advanced programming"
  * 5. Create a program (or a method) that counts the number of students that have passed the course "Data structures" with a grade 4
  */
object Hello {
  def main(args: Array[String]): Unit = {
    val D = readDataSet
    
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
