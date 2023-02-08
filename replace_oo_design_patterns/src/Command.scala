object Command extends App {
  /*
  The command pattern involves creating a command interface, a client to create the command, an invoker to run the
  command and keep track of the history of run commands, and a receiver that the command runs on/affects.  With
  functional programming, we can use functions instead of a command interface to simplify things
  * */

  // The receiver object.  For simplicity, we'll assume that the cash amount is an integer and cash can only be added
  class CashRegister(var total: Int = 0) {
    def addCash(amount: Int): Unit = total += amount
    def clear(): Unit = total = 0
  }

  // The command.  Instead of implementing some form of interface, we just use a function.  Note that we also use
  // currying since we'll often just use the same cash register
  def makePurchase(register: CashRegister)(amount: Int): () => Unit = {
    () => {
      println(s"Purchase amount: $amount")
      register.addCash(amount)
    }
  }

  // Vector of purchase functions.  This can be viewed as part of the invoker because we keep track of the history of
  // purchases (in case we need to recreate them, log them, etc.)
  var purchases: Vector[() => Unit] = Vector()

  // Functions that stores the purchase functions in the purhcases vector and executes the given purchase function
  def executePurchase(purchase: () => Unit) = {
    purchases :+= purchase
    purchase()
  }

  // Create a cash register and a partially-applied version of makePurchase (that uses the cash register)
  val register = new CashRegister()
  val makePurchaseWithRegister = makePurchase(register)(_)

  // A couple of purchase functions we would like to execute
  val purchase1 = makePurchaseWithRegister(100)
  val purchase2 = makePurchaseWithRegister(50)

  // Execute and store the purchases
  executePurchase(purchase1)
  executePurchase(purchase2)

  // Check the register total (it should be 150)
  println(s"Register total: ${register.total}")

  // If we reset the register amount, we can still execute the purchases again because we kept track of them
  register.clear()
  println(s"Register total after reset = ${register.total}")
  purchases.foreach(purchase => purchase())
  println(s"Register total after rerunning purchases: ${register.total}")  // Should be 150 again
}