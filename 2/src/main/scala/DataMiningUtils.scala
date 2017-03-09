/**
  * Some data mining utils implemented in an abstract way
  * @tparam T
  */
class DataMiningUtils[T <: Comparable[T]](D: List[Set[T]]) {

  /**
    * Determines the confidence of the given rule
    * @param rule tuple of the form (A,B)
    * @return the confidence of "A -> B"
    */
  def confidence(rule: (Set[T], Set[T])) = {
    supportOfRuleInD(rule) / supportOfSetInD(rule._1)
  }

  /**
    * Determines the support of the given set
    * @param set the set to count
    * @return the support of above set
    */
  def supportOfSetInD(set: Set[T]) = {
    D.count(transaction => set.subsetOf(transaction)).toDouble / N
  }

  /**
    * Determines the support of the given rule
    * @param rule tuple of the form (A,B)
    * @return the support of "A -> B"
    */
  def supportOfRuleInD(rule: (Set[T], Set[T])) = {
    supportCountOfRuleInD(rule).toDouble / N
  }

  /**
    * Determines the support count of the given rule
    * @param rule tuple of the form (A,B)
    * @return the support count of "A -> B"
    */
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
  /**
    * Computes a sorted list of basic elements in D
    * @return the sorted item list of D
    */
  def sortedItemList = IS.toList.sorted


}

