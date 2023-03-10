import scala.collection.mutable
import scala.util.Random

object FocusedMutability extends App {
  /*
  * While working with immutable objects is preferred, they usually cause slower run times.  Although this is often
  * negligible, especially due to modern computer power, sometimes strategically using a mutable object in place of
  * an immutable one can be beneficial.  For example, if you have a piece of code that is called by clients frequently,
  * it might make sense to use mutable objects in an effort to boost performance.  This strategy is known as "Focused
  * Mutability".  In the given example, we have a Purchase object representing purchases various customers make.  We
  * would like to, given a sequence of purchases, create a map from store ID to list of purchases.  In the
  * "immutableSequenceProcessing" function, we do this with an immutable map.  In the "mutableSequenceProcessing"
  * function, we do the same but with a mutable map.  For both, we run 5 runs with 100000 purchases and time
  * the function calls.  From the output, you can see that the mutable version is slightly faster.
  * */
  case class Purchase(storeId: Int, customerId: Int, itemId: Int)

  val rand = new Random()

  def createRandomPurchase(): Purchase = Purchase(rand.nextInt(100), rand.nextInt(1000), rand.nextInt(500))

  // Note that we are using the lazy sequence pattern to generate purchases
  def createPurchases(): Stream[Purchase] = createRandomPurchase() #:: createPurchases()

  def immutableSequenceProcessing(numPurchases: Int): Map[Int, List[Purchase]] = {
    val purchases = createPurchases().take(numPurchases)
    var purchaseMap: Map[Int, List[Purchase]] = Map()

    purchases.foreach { purchase =>
      purchaseMap.get(purchase.storeId) match {
        case None => purchaseMap = purchaseMap + (purchase.storeId -> List(purchase))
        case Some(existingPurchases) => purchaseMap = purchaseMap + (purchase.storeId -> (existingPurchases :+ purchase))
      }
    }

    purchaseMap
  }

  def mutableSequenceProcessing(numPurchases: Int): Map[Int, List[Purchase]] = {
    val purchases = createPurchases().take(numPurchases)
    val purchaseMap: mutable.Map[Int, List[Purchase]] = mutable.Map()

    purchases.foreach { purchase =>
      purchaseMap.get(purchase.storeId) match {
        case None => purchaseMap.put(purchase.storeId, List(purchase))
        case Some(existingPurchases) => purchaseMap.put(purchase.storeId, existingPurchases :+ purchase)
      }
    }

    // Convert back to an immutable map at the end
    purchaseMap.toMap
  }

  def timeFunction[T](func: => T, numRuns: Int): Unit = {
    for (_ <- 0 until numRuns) {
      val start = System.nanoTime()
      func
      val end = System.nanoTime()
      val elapsedTimeInMs = (end - start) * 0.000001

      println(s"Elapsed time (ms) is $elapsedTimeInMs")
    }
  }

  val numPurchases = 100000
  val numRuns = 5
  println("Immutable map times:")
  timeFunction(immutableSequenceProcessing(numPurchases), numRuns)
  println("Mutable map times:")
  timeFunction(immutableSequenceProcessing(numPurchases), numRuns)
}