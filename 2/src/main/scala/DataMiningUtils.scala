/**
  * Some data mining utils implemented in an abstract way
  * @tparam T
  */
class DataMiningUtils[T] {
  def confidence(rule: (Set[T], Set[T]), D: List[Set[T]]) = {
    supportOfRuleInD(rule, D) / supportOfSetInD(rule._1, D)
  }

  def supportOfSetInD(set: Set[T], D: List[Set[T]]) = {
    D.count(transaction => set.subsetOf(transaction)).toDouble / N(D)
  }

  def supportOfRuleInD(rule: (Set[T], Set[T]), D: List[Set[T]]) = {
    supportCountOfRuleInD(rule, D).toDouble / N(D)
  }

  def supportCountOfRuleInD(rule: (Set[T], Set[T]), D: List[Set[T]]) = {
    val union = rule._1 union rule._2
    D.count(transaction => union.subsetOf(transaction))
  }

  def N(D: List[Set[T]]) = D.count(_ => true)

}
