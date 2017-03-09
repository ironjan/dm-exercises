

object Main {

  val booktitles = Map(
    "T" -> "The manly art of knitting",
      "W" -> "Why cats paint",
    "H" -> "How to make money in your spare time",
    "I" -> "Invisible dick (Richard)",
    "P" -> "Pooh gets stuck",
    "E" -> "Eating people is wrong",
    "O" -> "Old tractors and the men who love them"
  )

  def main(args: Array[String]): Unit = {
    val D = (new BookDataSetReader).readDataSet("./data.csv")

    val utils = new DataMiningUtils[String]


    println(s"Read ${utils.N(D)} entries.")

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

  def countStudentsWith(D: List[Registration], s: String, minGrade: Int) = D.count(_.passedWithMinGrade(minGrade, s))



}
