import akka.actor.Actor._
import akka.actor.FSM.Shutdown
import akka.actor.{Actor, ActorSystem, Props}

case object CreateActors
class ParentActor extends Actor{
  def receive = {
    case CreateActors =>
      println("Creating child actors")
      (1 to 5).foreach(_=> {
        val child = context.actorOf(Props[ChildActor])
        child ! "Hello"
      })

    case Shutdown =>
      println("shutting down")
      context.system.terminate()
    case _ =>
      println("Parent actor received an unknown message")
  }

}

class ChildActor extends Actor {
  def receive ={
    case message: String =>
      println(s"Child actor received: $message")
    case _=>
      println("child actor received unknown message")
  }

}

object myobj5 extends App{
  val system = ActorSystem("ParentActorSystem")
  val parent =  system.actorOf(Props[ParentActor])

  parent ! CreateActors
  parent ! Shutdown
}
