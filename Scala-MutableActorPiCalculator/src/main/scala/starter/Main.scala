package starter
import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }
import scala.concurrent.duration._

object Main extends App {
  val numberOfElements = 20000
  val tStart = System.currentTimeMillis()
  val piCalculator = new PiCalculator(numberOfElements)
  piCalculator.process()
  val tEnd = System.currentTimeMillis()
  System.out.println("Result: " + piCalculator.getPi);
  System.out.println("Duration: " + (tEnd - tStart) + "ms");
}