import scala.util.control.TailCalls.{TailRec, done, tailcall}

object MutualRecursion extends App {
  /*
  * In most cases, tail recursion is enough to accomplish our goals (simple iteration).  However, for more complex
  * problems, we might have functions that can call each other recursively.  In order to avoid running out of call
  * stack space (similar to the motivation behind tail recursion), we can use mutual recursion.  In Scala, this
  * involves using "tailcall" when making recursive calls and "done" when a base case is reached.  To get the actual
  * end result, we have to call "result" on a TailRec instance.
  * */

  /*
  * This is obviously a terrible way to check if a number is even or odd, but it's simple enough to see how to use
  * mutual recursion.  A more realistic example might be something like a finite state machine.
  * */
  def even(n: Long): TailRec[Boolean] = {
    if (n == 0) {
      done(true)
    } else {
      tailcall(odd(n - 1))
    }
  }

  def odd(n: Long): TailRec[Boolean] = {
    if (n == 0) {
      done(false)
    } else {
      tailcall(even(n - 1))
    }
  }

  println(even(0).result)
  println(even(1001).result)
  println(odd(1001).result)
  println(odd(100001).result)
}