object CustomizedControlFlow extends App {
  /*
  * Creating custom control flow can get messy.  Thankfully, with functional programming, it can be fairly
  * straightforward through the use of higher-order functions (functions that take other functions as arguments).  In
  * the following example, we have a function that chooses between 3 different functions to call, depending on the
  * value of x.  We use the : => T syntax to denote that each of the supplied functions are passed by name; this means
  * that Scala will not execute the functions when they are passed into the choose function, but will execute them
  * once they are called within the choose function.  We saw a similar approach in the timeFunction function in
  * FocusedMutability.scala (it was a higher-order function that timed n runs of a supplied function).
  * */
  def choose[T](x: Int, function1: => T, function2: => T, function3: => T): T = {
    x match {
      case 1 => function1
      case 2 => function2
      case _ => function3
    }
  }

  val function1: Unit = println("Hello world")
  val function2: Unit = println("Foo bar baz")
  val function3: Unit = println("Goodbye world")


  choose(1, function1, function2, function3)  // Should print "Hello world" since x is 1
  choose(2, function1, function2, function3)  // Should print "Foo bar baz" since x is 2
  choose(3, function1, function2, function3)  // Should print "Goodbye world" since x is 3
}
