import scala.collection.mutable.Stack
object StackObject {
  def main(args: Array[String]): Unit = {
    var myStack = new Stack[Int]
    myStack.push(89)
    println("Number of items: " + myStack.length)
    println("Item at the top of the stack: " + myStack.top)

    myStack.push(21)
    println("Number of items: " + myStack.length)
    println("Item at the top of the stack: " + myStack.top)

    var itemFromStack = myStack.pop
    println("Popped item: " + itemFromStack)
    println("Number of items: " + myStack.length)
    println("Item at the top of the stack: " + myStack.top)

    myStack.push(44)
    println("Number of items: " + myStack.length)
    println("Item at the top of the stack: " + myStack.top)

    while (myStack.length != 0)
      println("Popped item: " + myStack.pop)
  }
}