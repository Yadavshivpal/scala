import scala.collection.mutable.Queue
object QueueObject {
  def main(args: Array[String]): Unit = {

    var myQueue = new Queue[Int]

    myQueue.enqueue(47)
    println("Number of items: " + myQueue.length)
    println("Item at the front of the queue: " + myQueue.front)

    myQueue.enqueue(83)
    println("Number of items: " + myQueue.length)
    println("Item at the front of the queue: " + myQueue.front)

    var itemFromStack = myQueue.dequeue
    println("Dequeued item: " + itemFromStack)
    println("Number of items: " + myQueue.length)
    println("Item at the front of the queue: " + myQueue.front)

    myQueue.enqueue(23)
    println("Number of items: " + myQueue.length)
    println("Item at the front of the queue: " + myQueue.front)
    while (myQueue.length != 0)
      println("Dequeued item: " + myQueue.dequeue)
  }
}