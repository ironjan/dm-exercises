/**
  * Some data mining utils implemented in an abstract way
  * @tparam T
  */
class DataMiningUtils[T](D: List[Set[T]]) {
  
  def confidence(rule: (Set[T], Set[T])) = {
    supportOfRuleInD(rule) / supportOfSetInD(rule._1)
  }

  def supportOfSetInD(set: Set[T]) = {
    D.count(transaction => set.subsetOf(transaction)).toDouble / N
  }

  def supportOfRuleInD(rule: (Set[T], Set[T])) = {
    supportCountOfRuleInD(rule).toDouble / N
  }

  def supportCountOfRuleInD(rule: (Set[T], Set[T])) = {
    val union = rule._1 union rule._2
    D.count(transaction => union.subsetOf(transaction))
  }

  /**
    * Counts the transactions
    * @return number of transactions
    */
  def N = D.count(_ => true)

  /**
    * Computes the item set of D
    * @return the item set of D
    */
  def IS = D.flatten.distinct.toSet

}

