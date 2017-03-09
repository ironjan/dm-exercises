package com.example


object Hello {
  val IntroductionToProgramming = "Ohjelmoinnin_perusteet"
  val AdvancedProgramming = "Aineopintojen_harjoitusty_Ohjelmointi"
  val DataStructures = "Tietorakenteet_ja_algoritmit"

  def main(args: Array[String]): Unit = {
    val D = parseDataSet(readDataSet)
    warmUpExercises(D)


  }

  private def warmUpExercises(D: List[Registration]) = {
    printDifferentCourseCodes(D)

    // 2. Create a program (or a method) that counts the number of students that have attempted the course "Introduction to programming"
    println("Attempted Introduction to Programming: " + D.count(_.attempted(IntroductionToProgramming)))

    // 3. Create a program (or a method) that counts the number of students that have passed the course "Introduction to programming"
    println("Passed Introduction to Programming: " + D.count(_.passed(IntroductionToProgramming)))

    // 4. Create a program (or a method) that counts the number of students that have passed the course "Advanced programming"
    println("Passed Advanced programming: " + D.count(_.passed(AdvancedProgramming)))

    // 5. Create a program (or a method) that counts the number of students that have passed the course "Data structures" with a grade 4
    println("Passed Data structures: " + D.count(_.passedWithMinGrade(4, DataStructures)))

    // 7. Create a program (or a method) that counts the number of students that have passed the courses "Introduction to programming" and "Advanced programming"
    println("Passed Introduction to Programming and Advanced Programming: " + D.count(_.passed(IntroductionToProgramming, AdvancedProgramming)))

    // 8. Create a program (or a method) that counts the number of students that have passed the courses "Introduction to programming", "Advanced programming" and "Data structures"
    println("Passed Introduction to Programming, Advanced Programming, and Data structures: " + D.count(_.passed(IntroductionToProgramming, AdvancedProgramming, IntroductionToProgramming)))

    // 9. Create a program (or a method) that counts the number of students that have passed the courses "Introduction to programming", "Advanced programming" and "Data structures", all with a grade 4
    println("Passed Introduction to Programming, Advanced Programming, and Data structures *with 4*: " + D.count(_.passedWithMinGrade(4, IntroductionToProgramming, AdvancedProgramming, IntroductionToProgramming)))

    // 10. If you haven't already done so, refactor your code so that you can count the number of students that have attended any given set of courses with a given grade interval.
  }

  /**
    * 1. Write an application that [...] prints out all the different course_codes in the file.
    */
  private def printDifferentCourseCodes(D: List[Registration]): Unit = {
    println("Course Codes:")

    val printed = D.flatMap(r => r.courseRecords)
      .map(_.code)
      .distinct
      .sorted
      .mkString(", ")
    println(s"$printed")
  }

  def countStudentsWith(D: List[Registration], s: String, minGrade: Int) = D.count(_.passedWithMinGrade(minGrade, s))


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
