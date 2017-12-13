package Akka

import Akka.MasterActor.{Start, Stop}
import IO.Console
import akka.actor.{Actor, Props}

object ConsoleActor {
  def props: Props = Props(new ConsoleActor)

  case object GetCipherCommand

  case object CheckIfQuit
  case class Print(s: String)

}

class ConsoleActor extends Actor {

  import ConsoleActor._

  def receive = {
    case Print(s) => Console.output(s)
    case CheckIfQuit => if (Console.checkIfQuit()) sender ! Stop else sender ! Start
    case GetCipherCommand => sender ! Console.readCipherCommand()
  }
}
