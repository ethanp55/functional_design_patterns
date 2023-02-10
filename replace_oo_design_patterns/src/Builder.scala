object Builder extends App {
  /*
  In non functional programming languages, a pattern called Fluent Builder is often used when we want to create
  immutable objects.  For example, Java's standard library uses it with StringBuilder and StringBuffer.  Rather than
  worrying about implementing this pattern, which can be complicated if the number of data fields is large, we can use
  built-in tools of a functional programming language (i.e. Scala) to create immutable objects.  In fact, it is
  typically encouraged to use immutable objects over mutable ones, as side effects often produce bugs and other
  unexpected behavior.
  * */

  // In scala, we can specify parameters as vals to indicate they are immutable
  class Person(val firstName: String, val middleName: String, val lastName: String)

  /*
  We can also use case classes, which make parameters val by default.  Case class are preferred when you want to
  represent immutable data, as they can be used with Scala's pattern matching, contain default implementations for
  common methods, etc.
  * */

  case class PersonCaseClass(firstName: String, middleName: String, lastName: String)  // Notice that we don't have to specify val for each parameter
}