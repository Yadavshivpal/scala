object ListOprations {
  def main(args: Array[String]) = {
    var integerList: List[Int] = List(100, 101, 102)
    var Cities: List[String] = List(
      "Dehli",
      "Mumbai",
      "Pune",
      "Bhopal")
    Cities = Cities.:::(List[String]("Jhansi"))
    println(Cities)
    for (i <- integerList)
      println("Element: " + i)
  }

}
