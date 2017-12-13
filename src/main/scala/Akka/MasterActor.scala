package Akka

import Models.{CipherCommand, Message}
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object MasterActor {
  def props(cipherActor: ActorRef, consoleActor: ActorRef, system: ActorSystem): Props =
    Props(new MasterActor(cipherActor, consoleActor, system))

  case object Start

  case object Stop

}

class MasterActor(cipher: ActorRef, console: ActorRef, system: ActorSystem) extends Actor {

  import ConsoleActor._
  import MasterActor._

  def receive = {
    case Stop => system.terminate
    case Start => console ! GetCipherCommand
    case command: CipherCommand => cipher ! command
    case Message(m) =>
      console ! Print(m)
      console ! CheckIfQuit
  }
}
