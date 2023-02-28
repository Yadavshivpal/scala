abstract class Shape {
  type id
  def computeArea: Float
  def computePerimeter: Float
}
class Circle(radius: Float) extends Shape {
  var id: Int = 0
  def computeArea: Float = {
    return 3.14159265359f * radius * radius
  }
  def computePerimeter: Float = {
    return 2 * 3.14159265359f * radius
  }
}
object Area {
  def main(args: Array[String]): Unit = {
    var circle: Circle = new Circle(6)
    println("Area of Circle: " + circle.computeArea)
  }
}