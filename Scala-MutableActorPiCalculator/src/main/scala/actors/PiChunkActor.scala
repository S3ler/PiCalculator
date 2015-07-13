package actors
import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }
import scala.concurrent.duration._

case class chunkNrMsg(elementNumber: Int)

class PiChunkActor extends Actor{
    
     def receive = {
       case chunkNrMsg(chunkNr) => 
        var chunk = 4.0d * (1.0d - (chunkNr.toDouble % 2.0d) * 2.0d) / (2.0d * chunkNr.toDouble + 1.0d)
        sender ! ChunkResult(chunk) // or: reply(element)
     }
    
}