object SetObject {
  def main(args: Array[String]): Unit = {

    val evenNumbers = Set(2, 4, 6, 8, 10, 12, 14)
    // Print out some properties:
    println("Head: " + evenNumbers.head)
    println("Tail: " + evenNumbers.tail)
    println("IsEmpty: " + evenNumbers.isEmpty)

    if (evenNumbers.contains(3))
      println("Set contains 3!")
    else
      println("Set does not contain 3...")
    
    if (evenNumbers.contains(2))
      println("Set contains 2!")
    else
      println("Set does not contain 2...")
  }
}