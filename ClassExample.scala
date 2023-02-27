class ClassExample(name: String,age: Int) {
  def getName(): String = name
  def getAge(): Int = age
  def sayHello() = {
    println(s"Hello, My name is $name and I am $age years old.")
  }
}
object Main {
  def main(args: Array[String]): Unit = {
    val classExample = new ClassExample("Shiv", 24)
    println(classExample.getName())
    println(classExample.getAge())
    classExample.sayHello()
  }
}
