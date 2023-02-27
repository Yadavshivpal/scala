import scala.io.StdIn._
object ArraySum {
  def main(args: Array[String]): Unit = {
    SumArray()
  }
  def SumArray(): Unit = {
    println("Enter the size of the array:")
    val size = readInt()
    var array = new Array[Int](size)
    for(i <- 0 until size)
      {
        print(s"Enter element $i ")
        array(i) = readInt()
      }
      var sum = 0
    for(i <- array)
      {
        sum += i
      }
      println(s"sum is $sum")
  }

}
