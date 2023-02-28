class Cars (val name: String, val model: String, val year: Int ){
  def drvie(): Unit = println(s"car info is like name: $name, model: $model, year: $year ")
}

class SportCars(name: String, model: String, year: Int, topSpeed: Int) extends  Cars(name, model, year){
   def drive(): Unit = println(s"Sport Car info is like name: $name, model: $model, year: $year, topSpeed:$topSpeed")
}
object ClassInheri {
  def main(args: Array[String]): Unit = {
    val car  = new Cars("Honda", "Civic", 2021)
    car.drvie()
    val sportCars = new SportCars("ferrari","458 Italian",2015,202)
    sportCars.drvie()
  }
}