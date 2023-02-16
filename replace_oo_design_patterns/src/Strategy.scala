object Strategy extends App {
  /*
  * Class representing a person.  We might have missing information about them, hence the Option[String] for each
  * attribute.
  * */
  case class Person(firstName: Option[String], middleName: Option[String], lastName: Option[String])

  // Validation function for checking if all aspects of a person's name are defined (i.e. a specific strategy)
  def fullNameIsValid(person: Person): Boolean = {
    person match {
      case Person(firstName, middleName, lastName) => firstName.isDefined && middleName.isDefined && lastName.isDefined
    }
  }

  // Function that takes a validation function/strategy and returns a function that uses the validator to filter a person
  def personCollector(validator: Person => Boolean): Person => Option[Person] = {
    (person: Person) => Some(person).filter(validator)
  }

  // Collector that uses the full name validation function/strategy
  val fullNameCollector = personCollector(fullNameIsValid)

  // Make a few people; note that p2 does not have a middle name
  val p1 = Person(Some("John"), Some("Stacy"), Some("Adams"))
  val p2 = Person(Some("Franklin"), None, Some("Roosevelt"))
  val p3 = Person(Some("Jay"), Some("Edgar"), Some("Hoover"))
  val people = List(p1, p2, p3)

  // Use fullNameCollector to create a list of people with full names
  val validatedPeople = people.flatMap(fullNameCollector(_))

  // Should only contain p1 and p3
  println(s"People with full names: $validatedPeople")
}