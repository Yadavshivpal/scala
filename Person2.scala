package caseClass

case class Person2(name: String, age: Int)


object Person2{
  def apply(name: String): Person2 = {
    Person2(name,0)
  }

  def incrmentAge(p: Person2): Person2 ={
    p.copy(age = p.age+1)
  }

}
object obj3 extends App{
  val person1 = Person2("Suresh", 30)
  val person2 = Person2("Hitesh")
  val person3 = Person2.incrmentAge(person2)
  println(person1)
  println(person2)
  println(person3)
}
