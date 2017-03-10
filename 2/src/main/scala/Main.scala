

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

    val bookmining = new DataMiningUtils[String](D)

    // 5: Consider the book data given in the above link. The following picture has nodes that correspond to book sets
    // in the obvious way. For example, “EO” represents set {E, O} and EOP set {E, O, P}. Calculate the supports of the
    // book sets. Nodes “E” and “O” already have their supports marked down.

    Stream(0.05, 0.15, 0.25, 0.5, 0.8, 1)
      .map{s => 
          val start: Long = System.currentTimeMillis
          val frequent = new Apriori(bookmining, s).findFrequent
          val end: Long = System.currentTimeMillis

          (s, frequent.length, s"${end - start}ms")
      }
      .foreach(println)
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
