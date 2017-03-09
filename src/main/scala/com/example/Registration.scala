package com.example

case class Registration(registrationYear: Int,
                        courseRecords: List[CourseRecord])

object Registration {
  implicit class RegistrationOps(registration: Registration){
    def passed(courseNames: String*): Boolean = courseNames.map(passed).reduce(_ && _)
    def passed(courseName: String): Boolean = passedWithMinGrade(1, courseName)

    def attempted(courseNames: String*): Boolean = courseNames.map(attempted).reduce(_ && _)
    def attempted(courseName: String): Boolean = passedWithMinGrade(0, courseName)

    def passedWithMinGrade(minGrade: Int, courseNames: String*): Boolean =
      courseNames.map((courseName: String) => passedWithMinGrade(minGrade, courseName)).reduce(_ && _)

    def passedWithMinGrade(minGrade: Int, courseName: String): Boolean =
      registration.courseRecords.exists( c => c.name == courseName && c.finalGrade > minGrade)

  }
}