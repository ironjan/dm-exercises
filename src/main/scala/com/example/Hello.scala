package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println("registration_year [course_year_and_month course_code \"course name\" credits final_grade ]*")
    val bufferedSource = io.Source.fromFile("./data-2016.csv")
    for (line <- bufferedSource.getLines) {
      val cols = line.split(" ").map(_.trim).toList

      val registration_year = cols.head.toInt
      println(s"Registration: $registration_year")

      val registration_set = cols.tail.sliding(5).toList

      for(set <- registration_set){
        println(s" $set")
      }

    }
    bufferedSource.close
  }
}
