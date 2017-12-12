package Akka

import Cipher.Cipher
import IO.CipherMode
import Models.{Key, Message}
import akka.actor.{Actor, ActorRef, Props}


object CipherActor {
  def props(printerActor: ActorRef): Props = Props(new CipherActor(printerActor))

  case object Send

}

class CipherActor(printerActor: ActorRef) extends Actor {

  import CipherActor._

  var message: Message = Message("")

  def receive = {
    case (m: Message, k: Key, CipherMode.Encrypt) => message = Cipher.encrypt(m, k)
    case (m: Message, k: Key, CipherMode.Decrypt) => message = Cipher.decrypt(m, k)
    case Send => printerActor ! message
  }
}
