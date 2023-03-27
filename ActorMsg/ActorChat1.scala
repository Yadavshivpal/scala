import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.RoundRobinPool

import scala.collection.mutable
import scala.concurrent.duration.DurationInt
// Define the message that will be sent
case class MyMessage1(msg: String)
case class Confirmation(sender: ActorRef, numMessagesProcessed: Int)
case object RequestConfirmation
// Define the child actor that will receive the message
class CChildActor1 extends Actor {

  var messageProcessed: Int = 0
  def receive: Receive = {
    case MyMessage1(msg) =>
      println(s"${self.path.name} received message: $msg")
      messageProcessed += 1
      sender() ! Confirmation(sender(), messageProcessed)
    case RequestConfirmation =>
      println(s"${self.path.name} received RequestConfirmation message")
      sender() ! Confirmation(sender, messageProcessed)

  }
}

// Define the parent actor that will create the child actors and send the message
class PParentActor1 extends Actor {
  var confirmationsReceived: Int = 0
  var totalMessagesProcessed: Int = 0
  val childActors: mutable.Map[ActorRef, Int] = mutable.Map.empty
  def receive: Receive = {
    case "start" =>
      (1 to 10).foreach { j =>
        val childActor: ActorRef = context.actorOf(RoundRobinPool(10).props(Props[CChildActor1]), s"childActor$j")
        (1 to 10).foreach { i =>
          val msg = MyMessage1(s"message $i")
          childActor ! msg
        }
      }
    case RequestConfirmation =>
      println(s"ParentActor received Request Confirm msg")
      sender() ! Confirmation(sender, totalMessagesProcessed)
     if(childActors.nonEmpty) {
       childActors.keys.foreach { child =>
         //println(s"I am child printing myself $child")
         child ! RequestConfirmation
       }
     }

    case Confirmation(sender, numMessagesProcessed) =>
      println(s"${sender.path.name} processed $numMessagesProcessed messages")
      //println(s"Parent Actor received Confirmation message with $numMessagesProcessed messages processed")
      childActors.get(sender) match {
        case Some(processed) =>
          childActors.put(sender,processed+numMessagesProcessed)
        case None =>
          childActors.put(sender,numMessagesProcessed)
      }

      totalMessagesProcessed +=numMessagesProcessed
      confirmationsReceived += 1
      println(s"${sender.path.name} processed $numMessagesProcessed messages, $confirmationsReceived total msg: $totalMessagesProcessed" )
      if(confirmationsReceived == 100)
      {
        println("I am terminating")
        context.system.terminate()
      }
  }

  override def postStop(): Unit = {
    println(s"${self.path.name} stopped" )
  }
}

// Create the actor system and start the parent actor
object myObj7 extends App {
  val system = ActorSystem("MyActorSystem")
  val parentActor = system.actorOf(Props[PParentActor1], "parent")
  parentActor ! "start"
  system.scheduler.scheduleOnce(1.seconds, parentActor, RequestConfirmation)(system.dispatcher, parentActor)
}