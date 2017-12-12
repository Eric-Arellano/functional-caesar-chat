package Akka

import Models.{CipherCommand, Message}
import akka.actor.{Actor, ActorRef, Props}

object SupervisorActor {
  def props(cipherActor: ActorRef, consoleActor: ActorRef): Props = Props(new SupervisorActor(cipherActor, consoleActor))

  case object Start

  case object Stop

}

class SupervisorActor(cipher: ActorRef, console: ActorRef) extends Actor {

  import ConsoleActor._
  import SupervisorActor._

  def receive = {
    case Stop => System.exit(1)
    case Start => console ! GetMessage
    case command: CipherCommand => cipher ! command
    case Message(m) => console ! Print(m)
  }
}
