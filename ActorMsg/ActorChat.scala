import akka.actor.{Actor, ActorRef, ActorSystem, Props}
// Define the message that will be sent
case class MyMessage(msg: String)
case object AllMessagesProcessed
// Define the child actor that will receive the message
class CChildActor(parentActor: ActorRef) extends Actor {

  var messageProcessed: Option[Int] = None
  def receive: Receive = {
    case MyMessage(msg) =>
      println(s"${self.path.name} received message: $msg")
      messageProcessed = messageProcessed match {
        case Some(count) => Some(count+1)
        case None => Some(1)
      }
      println(s"$messageProcessed")
      if(messageProcessed.contains(100)){
        println(s"$messageProcessed")
        parentActor ! AllMessagesProcessed
      }
  }
}

// Define the parent actor that will create the child actors and send the message
class PParentActor extends Actor {
  var confirmationsReceived: Int = 0
  def receive: Receive = {
    case "start" =>
      // Create 10 child actors
      val childActors: Seq[ActorRef] = (1 to 10).map { i =>
        context.actorOf(Props(new CChildActor(self)), s"child-$i")
      }

      // Send 100 messages to the child actors
      (1 to 100).foreach { i =>
        val msg = MyMessage(s"message $i")
        // Send the message to a random child actor
        val randomChild = childActors(scala.util.Random.nextInt(childActors.size))
        randomChild ! msg
      }
    case "AllMessagesProcessed" =>
      confirmationsReceived += 1
      if(confirmationsReceived == 10)
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