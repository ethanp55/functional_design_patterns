object FunctionalInterface extends App {
  // Functional Interface is a design pattern where non functional programming languages (Java, etc.) try to mimic the
  // ability of functional programming languages to treat functions like variables.  With a functional programming
  // language like Scala, we do not need a pattern for this (it's already built into the language itself)

  // For example, if we want to create a comparator in a non functional programming language, we have to be a lot more
  // verbose.  In functional language like Scala, we can very easily do this with something like the sortWith method
  case class Person(firstName: String, lastName: String)

  val p1 = Person("Jake", "Armstrong")
  val p2 = Person("Amanda", "Smith")
  val p3 = Person("Brian", "Zitzel")

  val people = Vector(p1, p2, p3)

  val sortedByFirstName = people.sortWith((x, y) => x.firstName < y.firstName)

  println(s"People when sorted by first name: $sortedByFirstName")

  // If we want a more complicated sorting logic, like something that sorts by first name and then last name, we can
  // create a function and just pass that to the sortWith method (because we can pass functions around like variables)
  def sortByFirstThenLast(x: Person, y: Person) = {
    if (x.firstName != y.firstName) {
      x.firstName < y.firstName
    } else {
      x.lastName < y.lastName
    }
  }

  val otherPeople = Vector(p1, p2, p3, Person("Amanda", "Peterson"))
  val sortedByFirstThenLast = otherPeople.sortWith(sortByFirstThenLast)  // This is where we can pass our function like a variable

  println(s"People when sorted by first and then last name: $sortedByFirstThenLast")
}

