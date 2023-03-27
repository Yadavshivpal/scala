import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.RoundRobinPool

import scala.collection.mutable
import scala.concurrent.duration.DurationInt

case class MyMessage1(msg: String)
case class Confirmation(sender: ActorRef, numMessagesProcessed: Int, childActor: ActorRef)
case object RequestConfirmation

class CChildActor1 extends Actor {

  var messageProcessed: Int = 0
  def receive: Receive = {
    case MyMessage1(msg) =>
      println(s"${self} received message: $msg")
      messageProcessed += 1
      sender() ! Confirmation(sender(), messageProcessed, self)
    case RequestConfirmation =>
      println(s"${self.path.name} received RequestConfirmation message")
      sender() ! Confirmation(sender, messageProcessed, self)
  }
}

class PParentActor1 extends Actor {
  var confirmationsReceived: Map[ActorRef, Int] = Map.empty.withDefaultValue(0)
  var totalMessagesProcessed: Int = 0
  val childActors: mutable.Map[ActorRef, Int] = mutable.Map.empty

  def receive: Receive = {
    case "start" =>
        val childActor: ActorRef = context.actorOf(RoundRobinPool(10).props(Props[CChildActor1]), s"CHILD")
        (1 to 100).foreach { i =>
          val msg = MyMessage1(s"message $i")
          childActor ! msg
        }

    case RequestConfirmation =>
      println(s"ParentActor received Request Confirm msg")
      sender() ! Confirmation(sender, totalMessagesProcessed, self)

      if (childActors.nonEmpty) {
        childActors.keys.foreach { child =>
          println(s"I am child printing my self $child")
          child ! RequestConfirmation
        }
      }else
        {
          println("child actors are empty no request is sent ")
        }

    case Confirmation(sender, numMessagesProcessed, childActor) =>
      println(s"${sender.path.name} processed $numMessagesProcessed messages")
      childActors.put(childActor, childActors.getOrElse(childActor, 0) + numMessagesProcessed)

      confirmationsReceived = confirmationsReceived.updated(childActor, confirmationsReceived(childActor) + 1)
      println(s"${childActor.path.name} processed $numMessagesProcessed messages, ${confirmationsReceived(childActor)} total confirmations received")

      println(s"childActors: ${childActors.size}")
      println(s"ConfirmationReceived: ${confirmationsReceived.size}")
      totalMessagesProcessed += numMessagesProcessed
      println(s"$totalMessagesProcessed")
      if (totalMessagesProcessed == 100) {
        println("I am terminating")
        context.system.terminate()
      }

  }

  override def postStop(): Unit = {
    println(s"${self.path.name} stopped")
  }
}

object myObj7 extends App {
  val system = ActorSystem("MyActorSystem")
  val parentActor = system.actorOf(Props[PParentActor1], "parent")
  parentActor ! "start"
  system.scheduler.scheduleOnce(1.seconds, parentActor, RequestConfirmation)(system.dispatcher, parentActor)
}