object LazySequence extends App {
  /*
  * We usually deal with sequences one element at a time, which means we can often deal with them lazily (start
  * processing a sequence before the entire thing is realized).  This can help save memory by never actually storing
  * the entire sequence in memory.  Lazy sequencing only retrieves/creates an element in a sequence when it's asked for.
  * Scala's built-in support for lazy sequences is found in its Stream library.  When we wish to realize elements, we
  * simply access their values.  In this toy example, the function "addToStream" is an infinite recursive function.
  * Note however that we are using a stream instead of another sequence (otherwise, the code would crash).  The stream
  * will only create an element when we access it, allowing us to create an infinite function like this one.  Also note
  * that, similar to List prepending, we use the #:: operator (stream prepending).  Finally, if an element is already
  * realized by the stream, it uses caching to retrieve the same value later on if we try to access it again.
  * */
  def addToStream(x: Int): Stream[Int] = {
    println(s"Adding $x to stream")
    x #:: addToStream(x + 1)
  }

  println("Output when taking the first 5 elements:")
  val lazySeq = addToStream(1)
  val firstFive = lazySeq.take(5)
  firstFive.foreach(println(_))

  /*
  * Note that when we access the first 6 elements of the stream, the first 5 are pulled from cache (the
  * println(s"Adding $x to stream") statement in addToStream is only called for the last/sixth element since it has
  * not yet been realized).  This is because we already realized the first 5 elements in the code above.
  * */
  println("\nOutput when taking the first 6 elements:")
  val firstSix = lazySeq.take(6)
  firstSix.foreach(println(_))
}