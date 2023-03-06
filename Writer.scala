package OPP

class Writer(firstName: String, surName: String,val year: Int ) {
  def fullName(): String = firstName + " " + surName

}
class Novel(name: String, year: Int, author: Writer){
  def autherAge =  year - author.year
  def isWrittenBy(auther: Writer) = auther == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)

}
object NovelWriter extends App{
  val auther = new Writer("shiva", "yadav",1999)
  val novel = new Novel("Great Expectattions",2015, auther)
  println(novel.autherAge)
  println(novel.isWrittenBy(auther))
}