object Decorator extends App {
  /*
  * The decorator pattern is useful when we would like to extend the functionality of a class without actually
  * changing the class.  It is often called the wrapper pattern because traditionally the class is wrapped in
  * another class that contains the additional functionality.  With functional programming, we can use and wrap
  * functions instead of classes.
  * */

  // Say we have a calculator with 4 functions: add, subtract, multiply, and divide (on integers)
  def add(a: Int, b: Int): Int = a + b
  def subtract(a: Int, b: Int): Int = a - b
  def multiply(a: Int, b: Int): Int = a * b
  def divide(a: Int, b: Int): Int = a / b

  /*
  * Let's say now that we would like to add logging to the calculator.  Instead of modifying the functions, we can
  * decorate/wrap the functions in a new function that contains logging.  This function creates a new function that
  * performs the original calculator operation, but also prints/logs the result.
  * */
  def operationWithLogging(calculatorFunction: (Int, Int) => Int): (Int, Int) => Int = {
    (a: Int, b: Int) => {
      val result = calculatorFunction(a, b)
      println(s"Ran operation on $a and $b; result is $result")
      result
    }
  }

  // We can now create a wrapped function and call it
  val addWithLogging = operationWithLogging(add)
  addWithLogging(2, 3)
}