package starter
import akka.actor.{ ActorRef, ActorSystem, TypedActor, Props, Actor, Inbox }
import akka.actor._
import scala.concurrent.Await
import actors.PiResultActor
import actors.PiChunkActor
import actors._
import scala.concurrent.duration._
import akka.pattern.ask
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import akka.util.Timeout
import java.util.concurrent.TimeoutException

class PiCalculator(nrOfChunks: Int) {
  
  var result: Double = 0.0
  val system = ActorSystem("PiCalculator")
  val resultActor = system.actorOf(Props(classOf[PiResultActor], nrOfChunks), "piresult")
  val actors = List.fill(nrOfChunks)(system.actorOf(Props[PiChunkActor])) // http://stackoverflow.com/questions/6557169/how-to-declare-empty-list-and-then-add-string-in-scala

  def process() {

    for ((actor, i) <- actors.view.zipWithIndex) (actor.tell(chunkNrMsg(i), resultActor))

    implicit val timeout = Timeout(5 seconds)
    val future = resultActor ? AskForResult() // enabled by the “ask” import
    
    result = Await.result(future, timeout.duration).asInstanceOf[Double]
    val d = Duration.create(10, TimeUnit.MILLISECONDS);
    system.shutdown()
    while (!system.isTerminated) {
      try {
        system.awaitTermination(d);
      } catch {
        case e: TimeoutException => ;
      }
    }
  }

  def getPi = result
}