import akka.actor._

class MyActor extends Actor {
  def receive = {

    case "restart" =>
      throw new Exception("Force Restart")
    case message =>
      println(s"Received message: $message")

  }

  override def preStart(): Unit = {
    println("Actor is starting")
  }

  override def postStop(): Unit = {
    print("Actor is stopping")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Actor is forced to restart")
  }

  override def postRestart(reason: Throwable): Unit = {
    println("Actor is restarted")
  }
}
object MyApp extends App {
  val system = ActorSystem("mySystem")
  val myActor = system.actorOf(Props[MyActor], "myActor")
  myActor ! "Hello, World"
  Thread.sleep(1000)
  myActor ! "restart"
  Thread.sleep(1000)
  system.terminate()

}