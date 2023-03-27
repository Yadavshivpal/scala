import akka.actor.{Actor, ActorRef, ActorSystem, Props}
// Define the message that will be sent
case class MyMessage(msg: String)
case object AllMessagesProcessed
// Define the child actor that will receive the message
class CChildActor() extends Actor {

  def receive: Receive = {
    case MyMessage(msg) =>
      println(s"${self.path.name} received message: $msg")
      sender() ! AllMessagesProcessed
  }
}

// Define the parent actor that will create the child actors and send the message
class PParentActor extends Actor {
  var confirmationsReceived: Int = 0
  def receive: Receive = {
    case "start" =>
      (1 to 10).foreach { j =>
        val randomChild: ActorRef = context.actorOf(Props[CChildActor], s"childActor$j")
        (1 to 10).foreach { i =>
          val msg = MyMessage(s"message $i")
          randomChild ! msg
        }
      }
    case AllMessagesProcessed =>
      confirmationsReceived += 1
      if(confirmationsReceived == 100)
      {
        println("I am terminating")
        context.system.terminate();
      }
  }
}

// Create the actor system and start the parent actor
object myobj6 extends App {
  val system = ActorSystem("MyActorSystem")
  val parentActor = system.actorOf(Props[PParentActor], "parent")
  parentActor ! "start"
}