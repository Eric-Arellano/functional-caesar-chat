package Akka

import IO.Console
import akka.actor.{Actor, Props}

object ConsoleActor {
  def props: Props = Props(new ConsoleActor)

  case object GetMessage

  case class Print(s: String)

}

class ConsoleActor extends Actor {

  import ConsoleActor._

  def receive = {
    case GetMessage => sender ! Console.readCipherCommand()
    case Print(s) => Console.output(s)
  }
}
