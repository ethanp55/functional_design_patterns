import scala.annotation.tailrec

object TailRecursion extends App {
  /*
  * Iteration often requires mutability, which can be a problem in functional programming languages (they emphasize
  * immutability).  In order to get around this, we can use tail recursion.  Tail recursion works but creating a
  * recursive function where the final call in the function is a recursive call to itself (i.e. the recursion
  * aspect of the function is in the "tail" position).  This prevents multiple call stacks that we would see with
  * regular recursive functions, essentially creating a form of iteration.  Additionally, with scala, we can use the
  * @tailrec annotation to make sure our function is actually tail recursive (if it's not and we include the
  * annotation, the compiler will complain).
  * */

  // Function that returns the cumulative sum from 1 to upTo.  It's tail recursive, which is equivalent to iteration
  @tailrec
  def cumulativeSum(upTo: Int, currentSum: Int = 0): Int = {
    if (upTo == 0) {
      currentSum
    } else {
      cumulativeSum(upTo - 1, currentSum + upTo)
    }
  }

  println(s"The cumulative sum from 1 to 5 is ${cumulativeSum(5)}")
}