package OPP
//class parameters are not fields
class Person(val name: String, val age: Int) { //constructor
  //body
  val x = 2
  println(1+3)
  def greet(name: String): Unit = {
    println(s"${this.name} says: Hello $name")
  }
  def greet(): Unit =  println(s"hy, I am $name")

  //multiple constructors

  def this(name: String ) = this(name, 0)
  def this() =  this("Jhon Doe")
}
object OOBasic extends App{
  val person = new Person("Shiva", 26)
  println(person.age)
  person.greet("Daniel")
  person.greet()
}
