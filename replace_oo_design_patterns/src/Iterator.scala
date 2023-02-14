object Iterator extends App {
  /*
  * The iterator pattern is used to iterate through collections and, depending on the situation, performing some
  * operation on each element.  With a functional programming language like Scala, this becomes extremely simple
  * through operations such as map, flatMap, filter, etc.
  *
  * */

  // For example, say we would like to extract a set of vowels contained in a string:
  def isVowel(char: Char): Boolean = Set('a', 'e', 'i', 'o', 'u').contains(char)

  val string = "onomotopeia"
  val vowels = string.filter(isVowel).toSet  // We can simply call filter and pass in our isVowel checker function

  println(s"Vowels in $string: $vowels")


  // As another example, let's say we would like to prepend a greeting to a list of names:
  def prependGreeting(names: List[String]): List[String] = names.map(name => s"Hello $name")

  val names = List("Amanda", "Ernesto", "Billy")
  println(s"Names with greetings: ${prependGreeting(names)}")
}