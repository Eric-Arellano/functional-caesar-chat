package Akka

import Cipher.Cipher
import IO.CipherMode
import Models.{Key, Message}
import akka.actor.{Actor, ActorRef, Props}


object CipherActor {
  def props(caller: ActorRef): Props = Props(new CipherActor(caller))

}

class CipherActor(caller: ActorRef) extends Actor {

  def receive = {
    case (m: Message, k: Key, CipherMode.Encrypt) =>
      caller ! Cipher.encrypt(m, k)
    case (m: Message, k: Key, CipherMode.Decrypt) =>
      caller ! Cipher.decrypt(m, k)
  }
}
