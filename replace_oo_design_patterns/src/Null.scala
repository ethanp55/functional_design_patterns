object Null extends App {
  /*
  * Null checks are common in traditional programming languages, but can become quickly scattered throughout a
  * codebase.  If null checks do not exist, you can risk raising null pointer exceptions.  With a functional
  * programming language like Scala, we can make use of predefined data structures to handle null values.
  * Specifically, Scala has an Option container type where values can be either None or Some.  If we would like to
  * provide a default value, we could also use an Either.
  * */

  // We can create string Options, where one has a value and another is null
  val hasValue: Option[String] = Some("Foo")
  val isNone: Option[String] = None

  println(s"${hasValue.getOrElse("Empty")}")  // Should print "Foo" since it is not empty
  println(s"${isNone.getOrElse("Empty")}")  // Should print "Empty" (the default value) since it is null

  // We can also create an either that has either a left or right value (i.e. if we want to specify a default value)
  val hasValueEither: Either[String, String] = Left("Foo")
  val isNoneEither: Either[String, String] = Right("Empty")

  def printEither(x: Either[Any, Any]): Unit = {
    x match {
      case Left(value) => println(s"Either with left value: $value")
      case Right(value) => println(s"Either with right value: $value")
    }
  }

  printEither(hasValueEither)  // Should print a left value of "Foo"
  printEither(isNoneEither)  // Should print a right value of "Empty"
}