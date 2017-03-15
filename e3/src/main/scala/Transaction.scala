case class Transaction(user: String, visits: Seq[String])

object Transaction {

  implicit class TransactionOps(transaction: Transaction) {
    def contains(is: String*): Boolean = contains(is.toSet)

    def contains(is: Set[String]): Boolean = is.map(transaction.visits.contains).reduce(_ && _)
  }

}
