package caseClass
trait Person1
{
  val name: String
  val age: Int
}
case class Employee(name: String, age: Int, id: Int, salary: Double) extends Person1

object abc extends App {
  val employee = Employee("Shivpal", 25, 124, 50000.0)
  val name =  employee.name
  val age = employee.age
  val id = employee.id
  val salary =  employee.salary
  println(s"Eployee having id: $id name is: $name with age: $age and salary: $salary")
}