object NumbersPlay {
  def main(args: Array[String]) = {
    println("Enter your number:")
    val num = scala.io.StdIn.readInt()
    println(PlainDrome(num))
  }

  def PlainDrome(num: Int): String = {
    var revNumber = 0
    var temp = num
    while(temp > 0)
      {
        val rem = temp%10
        revNumber = revNumber * 10 + rem
        temp /= 10
      }
      if(num == revNumber) "Palindrome" else "Not Palindrome"
  }
}
