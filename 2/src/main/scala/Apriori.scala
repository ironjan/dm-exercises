class Apriori[T <: Comparable[T]](bookmining: DataMiningUtils[T], minSupport: Double = 0.0) {

  def fulfillMinSupport = (set: Set[T]) => bookmining.supportOfSetInD(set) >= minSupport


  def findFrequent: List[List[T]] = {
    val N = bookmining.N
    val minSupportCount = minSupport * N
    val itemset = bookmining.sortedItemList


    val F1 =
      itemset
        .map(Set(_))
        .filter(fulfillMinSupport)
        .map(_.toList.sorted)

    var F_kMinus1 = F1
    var Fs = List(F1)

    while(canGenerateCandidates(F_kMinus1)) {
      val generated = generateCandidates(F_kMinus1)
      val frequentPruned = frequentPrune(generated, F_kMinus1)
      val Fk = supportPrune(frequentPruned)
      Fs = Fk :: Fs
      F_kMinus1 = Fk
    }

    val result = Fs.flatten
    result
  }
  def frequentPrune(Fk: List[List[T]], F_kMinus1: List[List[T]]) = {
    val F_kMinus1Sets = F_kMinus1.map(_.toSet).toSet
    val k = F_kMinus1.head.length + 1

    Fk.filter(_.toSet.subsets(k - 1)
      .map(F_kMinus1Sets.contains)
      .reduce(_ && _))
  }

  private def canGenerateCandidates(F_kMinus1: List[List[T]]) = F_kMinus1.nonEmpty
  private def supportPrune(candidates: List[List[T]]) = {
    candidates
      .map(_.toSet)
      .filter(fulfillMinSupport)
      .map(_.toList.sorted)
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
