object Hello
{
	def main(args: Array[String]){
		
		val val1 = "Hello"
        val val2 = "Capital"
        
        val output1 = val1.compareTo(val2)
        println("output1 : " + output1)
        val output2= "Hello".compareTo(val1)
        println("output2 : " + output2)
        val output3 = val1.concat(" World")
        println("output3 : " + output3)
        output3.split(" ").foreach(println)
        val upper = "hello, world".map(c => c.toUpper)
         println("upper : " + upper)
		val result = "HelloWorld".charAt(3)
		println("Result : " + result)
	}
}
