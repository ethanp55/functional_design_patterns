object Memoization extends App {
  /*
  * Memoization is common in many algorithms and programming languages.  It is useful when we have expensive
  * computation and would like to cache the results for future calls on the same parameters/arguments.  For example,
  * expensiveLookup simulates a delay in looking up some data from, say, a database.  We would like to cache any
  * searched ID's in order to speed up computation the next time the same ID is called.  With scala we can memoize a
  * function by wrapping it in another function.
  * */
  def expensiveLookup(id: Int): Option[String] = {
    println(s"Doing expensive lookup for ID $id")
    Thread.sleep(3000)
    Map(42 -> "foo", 12 -> "bar", 1 -> "bax").get(id)
  }

  // Wraps the expensiveLookup function and provides memoization
  def memoizedLookup(): Int => Option[String] = {
    var cache: Map[Int, Option[String]] = Map()

    (id: Int) => {
      val result = cache.getOrElse(id, expensiveLookup(id))
      cache += id -> result
      result
    }
  }

  val mLookup = memoizedLookup()  // We have to instantiate an instance of the function to have access to the cache (under closure)

  println(mLookup(42))  // First call will call the expensiveLookup function
  println(mLookup(42))  // Second call will use the cache
  println(mLookup(5))  // Should call the expensiveLookup function again
}