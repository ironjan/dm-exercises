package com.example


object Hello {


  /** “Advanced programming” -- “Ohjelmoinnin jatkokurssi”, also “Java-ohjelmointi” */
  val AdvancedProgramming = "Ohjelmoinnin_jatkokurssi"

  /** “Introduction to programming” -- “Ohjelmoinnin perusteet” */
  val IntroductionToProgramming = "Ohjelmoinnin_perusteet"

  /** “Data structures” -- “Tietorakenteet ja algoritmit” */
  val DataStructures = "Tietorakenteet_ja_algoritmit"


  def main(args: Array[String]): Unit = {
    val D = parseDataSet(readDataSet)
    // warmUpExercises(D)


    val ia = Set(IntroductionToProgramming, AdvancedProgramming)
    val iad = Set(IntroductionToProgramming, AdvancedProgramming, DataStructures)

    println(s"N = ${N(D)}")

    val support = supportOfSetInD(ia, D)
    println(s"Support of {IntroductionToProgramming, AdvancedProgramming}: $support")

    // 13. Assume that there is a rule "if a student takes introductory programming course, then she will also take the
    // advanced programming course.". To how many students does this rule apply? Implement a program to determine that.
    val rule = (Set(IntroductionToProgramming), Set(AdvancedProgramming))
    val supportCount = supportCountOfRuleInD(rule, D)
    println(s"supportCount of {IntroductionToProgramming} -> {AdvancedProgramming}: $supportCount")

    // 14. Familiarize yourself with the concept of “Association rule” and find out how “Support” or “Relative Frequency”
    // is counted for such rules. Write also your explanation of these concepts into your learning diary.
    // Finally, write a program that determines the support for the rule {"Introduction to programming"} -> {"Advanced programming"}
    val supportRule = supportOfRuleInD(rule, D)
    println(s"support of {IntroductionToProgramming} -> {AdvancedProgramming}: $supportRule")

    // 18. Consider the statement “students that take the introductory programming course take the advanced programming
    // course in x% of the cases”. Given the dataset, what value is the x?
    val p1 = 100 * supportOfSetInD(ia, D) / supportOfSetInD(Set(IntroductionToProgramming), D)
    println(s"$p1% of students that take IntroductionToProgramming take AdvancedProgramming")

    // 19. Consider the statement “students that take the introductory programming course and the advanced programming
    // course take the data structures course in x% of the cases”. Given the dataset, what value is the x?
    val p2 = 100 * supportOfSetInD(iad, D) / supportOfSetInD(ia, D)
    println(s"$p2% of students that take IA take D")

    // 20. Familiarize yourself with the concept of “Confidence” (or “Rule strength”) and write a program that
    // determines the confidence for the rule {"Introduction to programming"} -> {"Advanced programming"}. When the
    // program has been finished, explain the concept “Confidence” in your learning diary.
    val conf = confidence(rule, D)
    println(s"conf({IntroductionToProgramming} -> {AdvancedProgramming}) = $conf")

    // 21-23. Write a program that determines the Confidence for any given rule A -> B, where A and B are sets of courses.

  }

  private def confidence(rule: (Set[String], Set[String]), D: List[Registration]) = {
    supportOfRuleInD(rule, D) / supportOfSetInD(rule._1, D)
  }

  private def supportOfSetInD(set: Set[String], D: List[Registration]) = {
    D.count(_.attempted(set)).toDouble / N(D)
  }

  def supportOfRuleInD(rule: (Set[String], Set[String]), D: List[Registration]) = {
    supportCountOfRuleInD(rule, D).toDouble / N(D)
  }

  def supportCountOfRuleInD(rule: (Set[String], Set[String]), D: List[Registration]) = {
    val union = rule._1 union rule._2
    D.count(_.attempted(union))
  }

  def N(D: List[Registration]) = D.count(_ => true)

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
