package Akka

import Cipher.Cipher
import Models.{CipherCommand, CipherMode}
import akka.actor.{Actor, Props}


object CipherActor {
  def props: Props = Props(new CipherActor)
}

class CipherActor extends Actor {

  def receive = {
    case CipherCommand(m, k, CipherMode.Encrypt) => sender ! Cipher.encrypt(m, k)
    case CipherCommand(m, k, CipherMode.Decrypt) => sender ! Cipher.decrypt(m, k)
  }
}
