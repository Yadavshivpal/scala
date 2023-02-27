object Array2D {
  def main(args: Array[String]): Unit = {
    val array2D = Array.ofDim[Int](5, 5)
    array2D(2)(0) = 50
    array2D(3)(4) = 40
    println("Element(0)(0): " + array2D(0)(0))
    println("Element(2)(0): " + array2D(2)(0))
    println("Element(3)(4): " + array2D(3)(4))
  }
}
