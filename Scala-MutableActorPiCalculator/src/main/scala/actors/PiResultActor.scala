package actors
import akka.actor.{ ActorContext, TypedProps, ActorRef, ActorSystem, TypedActor, Props, Actor, Inbox, PoisonPill }
import scala.collection.mutable.ListBuffer

case class ChunkResult(chunk: Double)
case class AskForResult()
case class AskResult(originSender: ActorRef)

class PiResultActor(nrOfChunks: Int) extends Actor {

  var finChunks = nrOfChunks
  var result: Double = 0.0

  def receive = {
    case ChunkResult(element: Double) =>
      receiveChunkResult(element)
    case AskForResult() =>
      receiveAskForResult(sender)
    case AskResult(originSender: ActorRef) =>
      receiveAskResult(originSender)
  }

  def receiveChunkResult(chunk: Double) = {
    result += chunk
    finChunks = finChunks - 1
    sender ! PoisonPill // or: reply(Shutdown()
  }

  def receiveAskForResult(originSender: ActorRef) = {
    self ! AskResult(originSender)
  }

  def receiveAskResult(originSender: ActorRef) = {
    if (finChunks == 0) {
      originSender ! result // return value to future
      self ! PoisonPill // stop itself
    } else {
      self ! AskResult(originSender) // send again to self
    }
  }

}