object MainObject {
  def main(args: Array[String]) = {
    var result = multiPly(15,2)
    println(result)
  }
  def multiPly(a:Int, b:Int):Int = {
    if(b == 0)
      0
    else
      a+multiPly(a,b-1)
  }
}