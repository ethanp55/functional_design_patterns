import FunctionalInterface.Person

object Closures extends App {
  // Takes in an array of comparison functions and returns a function that executes them in order.  We use map to run
  // the comparisons in order and find to search for the first one that is nonzero
  def makeComposedComparison(comparisons: (Person, Person) => Int*): (Person, Person) => Int = {
    (x: Person, y: Person) => comparisons.map(cmp => cmp(x, y)).find(_ != 0).getOrElse(0)
  }

  // We can now create different comparison functions and pass them to makeComposedComparison to create a function that
  // executes multiple comparisons in order
  val p1 = Person("John", "Adams")
  val p2 = Person("Mike", "Adams")

  def firstNameComparison(x: Person, y: Person): Int = x.firstName.compareTo(y.firstName)

  def lastNameComparison(x: Person, y: Person): Int = x.lastName.compareTo(y.lastName)

  val firstAndLastNameComparison = makeComposedComparison(firstNameComparison, lastNameComparison)

  println(firstAndLastNameComparison(p1, p2))
}
