class Apriori[T <: Comparable[T]](bookmining: DataMiningUtils[T], minSupport: Double = 0.0) {

  def fulfillMinSupport = (set: Set[T]) => bookmining.supportOfSetInD(set) >= minSupport


  def apriori: Unit = {
    val N = bookmining.N
    val minSupportCount = minSupport * N
    val itemset = bookmining.sortedItemList


    val F1 =
      itemset
        .map(Set(_))
        .filter(fulfillMinSupport)
        .map(_.toList.sorted)
    println(s"${itemset.length} candidates filtered to ${F1.length}")

    var F_kMinus1 = F1
    var Fs = List(F1)

    while(canGenerateCandidates(F_kMinus1)) {
      val Fk = supportPrune(frequentPrune(generateCandidates(F_kMinus1), F_kMinus1))
      Fs = Fk :: Fs
      F_kMinus1 = Fk
    }

    println(s"Generated ${Fs.flatten.length}")
  }
  def frequentPrune(Fk: List[List[T]], F_kMinus1: List[List[T]]) = {
    val F_kMinus1Sets = F_kMinus1.map(_.toSet).toSet

    println("Frequent prune")
    println(s"F_kMinus1: $F_kMinus1Sets")
    val pruned = Fk.filter{fk =>
      val fkSet = fk.toSet
      println(s"Checking $fkSet")
      val subsets = fkSet.subsets
      subsets.map { ss =>
        println(s"  $ss")
        F_kMinus1Sets.contains(ss)
      }.reduce(_ && _)
    }

    println(s"${Fk.length} candidates pruned by frequent to ${pruned.length}")

    pruned
  }

  private def canGenerateCandidates(F_kMinus1: List[List[T]]) = F_kMinus1.nonEmpty
  private def supportPrune(candidates: List[List[T]]) = {
    val pruned = candidates
      .map(_.toSet)
      .filter(fulfillMinSupport)
      .map(_.toList.sorted)
    println(s"${candidates.length} candidates pruned by support to ${pruned.length}")
    pruned
  }

  private def generateCandidates(F_kMinus1: List[List[T]]) = {
    // FIXME this will break when algorithm terminates ^^
    val k = F_kMinus1.head.length + 1

    F_kMinus1.flatMap { a =>
      F_kMinus1
        .flatMap { b =>
          // merge only if the first k - 2 items match
          if (a.slice(0, k - 2) == b.slice(0, k - 2)) {
            // ... and only merge with "bigger"
            if (a.last.compareTo(b.last) < 0) Some((b.last :: a).sorted) else None
          }
          else None
        }
    }
  }
}
