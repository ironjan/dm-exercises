

object Main {

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

    /* 7. List all subsequences of the sequence < {A} {B} {C} >
    *
    * < >
    * < {A} >
    * < {B} >
    * < {C} >
    * < {A} {B} >
    * < {A} {C} >
    * < {B} {C} >
    * < {A} {B} {C} >
    */

    /* 8. List all subsequences of the sequence < {A, B} {C} >
    *
    * < >
    * < {A} >
    * < {B} >
    * < {C} >
    * < {A} {C} >
    * < {B} {C} >
    * < {A, B} {C} >
    *
    * */

    /* 9. List all subsequences of the sequence < {A, B} {A, B} >
     *
     * < >
     * < {A} >
     * < {B} >
     * < {A} {A} >
     * < {A} {B} >
     * < {A} {A, B} >
     * < {B} {A} >
     * < {B} {B} >
     * < {B} {A, B} >
     * < {A, B} {A} >
     * < {A, B} {B} >
     * < {A, B} {A, B} >
     *
     * */

    /* 11. * What are all the possible 1- and 2-sequences for the events A, B, and C?
     *
     * < {A} >
     * < {B} >
     * < {C} >
     *
     * 3^1 1-seq
     *
     * < {A} {A} >
     * < {A} {B} >
     * < {A} {C} >
     * < {B} {A} >
     * < {B} {B} >
     * < {B} {C} >
     * < {C} {A} >
     * < {C} {B} >
     * < {C} {C} >
     *
     * 9 = 3^2 2-seq
     *
     * n items -> n^2 2-seqs
     */
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
  val is = D.flatMap(_.visits).toSet

  def e5 = {
    is.foreach(s => computeAndPrintSupportCount(s))
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
