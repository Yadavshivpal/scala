package caseClass

case class employee(name: String, val age: Int)

object Main {
  // Main method
  def main(args: Array[String]) = {
    var c = employee("Shivpal", 23)

    // Display both Parameter
    println("Name of the employee is " + c.name);
    println("Age of the employee is " + c.age);
  }
}
