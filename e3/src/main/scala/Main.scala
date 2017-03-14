

object Main {

  case class Transaction(user: String, visits: Seq[String])
   object Transaction{
     implicit class TransactionOps(transaction: Transaction){
       def contains(is: String*): Boolean =  contains(is.toSet)
       def contains(is: Set[String]): Boolean =  is.map(transaction.visits.contains).reduce(_ && _)
     }
   }
  val D = List(
    Transaction("A", Seq("index", "teaching", "courses", "dm")),
      Transaction("B", Seq("index", "teaching", "courses", "courses", "dm")),
        Transaction("C", Seq("dm", "courses", "dm")),
          Transaction("D", Seq("courses")),
            Transaction("E", Seq("index", "teaching", "courses")))

  def main(args: Array[String]): Unit = {
    println(D)

    e1
    e2
    e3
    e4
    e5
e6
  }

  /** 1. Does the web page sequence < index, teaching > exist in any of the students’ browsing records? If yes, for whom? */
  def e1 = {
    val result = D
      .filter(_.contains(Set("index", "teaching")))
      .map(_.user)

    println(result)
  }


  /** 2. What is the support count of < index >? */
  def e2 = {
    computeAndPrintSupportCount(Set("index"))
  }


  /** 3. What is the support count of < index, dm >? */
  def e3 = {
    computeAndPrintSupportCount(Set("index", "dm"))
  }

  /** 4. What is the support of < index, courses >? */
  def e4 = {
    computeAndPrintSupportCount(Set("index", "courses"))
  }

/** 5. List all 1-sequences (sequences containing exactly one page visit) in the data, and calculate their support counts. */
def e5 = {
  val seq1 = D.flatMap(_.visits).toSet
  seq1.foreach(s => computeAndPrintSupportCount(s))
}
  /** 6. Assume (only) the following 2-sequences, and fill in the support for the sequences where they do not exist. */
def e6 = {
val seq2 = D.flatMap(_.visits).toSet.subsets(2)
  seq2.foreach(s => computeAndPrintSupportCount(s))
}


  private def computeAndPrintSupportCount(strs: String*): Unit = computeAndPrintSupportCount(strs.toSet)
  private def computeAndPrintSupportCount(s: Set[String]): Unit = {
    val count = D.count(t => t.contains(s))
    println(s"support count of $s: $count")
  }
}
