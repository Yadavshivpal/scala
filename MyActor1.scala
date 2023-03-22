import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class MyActor1 extends Actor {
  def receive = {
    case "Hello" =>
      println("Received greeting from sender!")
      sender ! "Hi there!"
  }
}

object abc1 extends App {
  val system = ActorSystem("MyActorSystem")
  val actorRef = system.actorOf(Props[MyActor1], "MyActor1")

  // Send a message using ! pattern
  actorRef ! "Hello"

  // Send a message using ? pattern and get response in a Future
  implicit val timeout: Timeout = 3.seconds
  val future = actorRef ? "Hello"
  future.map(response => println(s"Received response: $response"))

  system.terminate()
}