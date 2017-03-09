package com.example


object Hello {
  val IntroductionToProgramming = "Ohjelmoinnin_perusteet"
  val AdvancedProgramming = "Aineopintojen_harjoitusty_Ohjelmointi"
  val DataStructures = "Tietorakenteet_ja_algoritmit"

  def main(args: Array[String]): Unit = {
    val D = parseDataSet(readDataSet)
    printDifferentCourseCodes(D)

    // 2. Create a program (or a method) that counts the number of students that have attempted the course "Introduction to programming"
    countStudentsWith(D, IntroductionToProgramming, 0)

    // 3. Create a program (or a method) that counts the number of students that have passed the course "Introduction to programming"
    countStudentsWith(D, IntroductionToProgramming, 1)

    // 4. Create a program (or a method) that counts the number of students that have passed the course "Advanced programming"
    countStudentsWith(D, AdvancedProgramming, 1)

    // 5. Create a program (or a method) that counts the number of students that have passed the course "Data structures" with a grade 4
    countStudentsWith(D, DataStructures, 4)
  }

  /**
    * 1. Write an application that [...] prints out all the different course_codes in the file.
    */
  private def printDifferentCourseCodes(D: List[Registration]): Unit ={
    println("Course Codes:")

    val printed = D.flatMap(r => r.courseRecords)
      .map(_.code)
      .distinct
        .sorted
      .mkString(", ")
      println(s"$printed")
  }

  def countStudentsWith(D: List[Registration], s: String, minGrade: Int) = {
    val number =
      D.map{d =>
        val filteredRecords =
          d.courseRecords
            .filter(_.name == s)
            .filter(_.finalGrade > minGrade)

        d.copy(courseRecords = filteredRecords)
      }
      .count(_.courseRecords.nonEmpty)

    println(s"$number students had min grade $minGrade in `$s`.")
  }

  private def parseDataSet(D: List[(String, List[List[String]])]) = {
    D.map { r =>
      val registrationYear = r._1.toInt
      val courseRecords = r._2.map(_.toArray).map(toCourseRecord)
      Registration(registrationYear, courseRecords)
    }
  }

  def readDataSet = {
    var D = List[(String, List[List[String]])]()
    val bufferedSource = io.Source.fromFile("./data-2016.csv")
    for (line <- bufferedSource.getLines) {
      val cols = line.split(" ").map(_.trim).toList

      val registration_year = cols.head

      val registration_set = cols.tail.sliding(5, 5).toList


      D = (registration_year, registration_set) :: D
    }
    bufferedSource.close

    D
  }

  def toCourseRecord(raw: Array[String]): CourseRecord =
    CourseRecord(raw(0),
      raw(1).toInt,
      raw(2).tail.init,
      raw(3).toDouble,
      raw(4).toInt)
}
