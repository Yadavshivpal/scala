object Numbers {
  def main(args: Array[String]) = {
    println("Enter your number:")
    val num = scala.io.StdIn.readInt()
    println(EvenOdd(num))
  }

  def EvenOdd(num: Int): String = {
    if (num % 2 == 0) "Even" else "Odd"
  }

}
