object  SeqExtension {
 implicit class StringSetSeqOps(seq: Seq[Set[String]]){
   def headSeqs = seq.head.subsets

 }
}
