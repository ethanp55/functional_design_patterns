object FunctionBuilder extends App {
  /*
  * Sometimes it can be useful to create a function that creates another function, allowing us to synthesize
  * behaviors on the fly.  In this example, applyDiscount is a function that creates a function which takes in a
  * price and returns a discounted price.
  * */
  def applyDiscount(discount: Double): Double => Either[Double, String] = {
    (originalPrice: Double) => {
      if (discount <= 0.0 || discount >= 1.0) {
        Right(s"Discount must be between 0 and 1; given discount is $discount")
      } else {
        Left(originalPrice - (originalPrice * discount))
      }
    }
  }

  def useDiscount(price: Double, discount: Double): Unit = {
    applyDiscount(discount)(price) match {
      case Left(updatedPrice) => println(s"Original price of $price is now $updatedPrice after applying discount of ${discount * 100}%")
      case Right(errorMessage) => println(s"Error with applying discount: $errorMessage")
    }
  }

  val discount = 0.25
  val discountApplier = applyDiscount(discount)
  val price = 100

  useDiscount(price, discount)
}