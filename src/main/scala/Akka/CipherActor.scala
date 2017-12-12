package Akka

import Cipher.Cipher
import Models.CipherCommand
import akka.actor.{Actor, Props}


object CipherActor {
  def props: Props = Props(new CipherActor)
}

class CipherActor extends Actor {

  def receive = {
    case command: CipherCommand => sender ! Cipher.convertMessage(command)
  }
}
