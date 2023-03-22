class Dog(val name: String)
object Dog extends App {
  def apply(name: String): Dog = new Dog(name)
  def fromBreed(breed: String): Dog = {
    val name =  breed match {
      case "Golden Retriever" => "Buddy"
      case "Labrador Retriever" => "Max"
      case "German Shepherd" => "Rocky"
      case _=>"Unknown"
    }
    Dog(name)
  }
}
object output extends App{
  val dog1 = Dog("Buddy")
  val dog2 =  Dog.fromBreed("German Shepherd")
  val dog3 =  Dog.fromBreed("Beagle")
  println(dog1.name)
  println(dog2.name)
  print(dog3.name)
}






