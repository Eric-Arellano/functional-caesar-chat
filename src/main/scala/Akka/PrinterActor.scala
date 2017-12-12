package Akka

import Models.Message
import akka.actor.{Actor, ActorLogging, Props}

object PrinterActor {
  def props: Props = Props[PrinterActor]
}

class PrinterActor extends Actor with ActorLogging {

  def receive = {
    case Message(m) =>
      log.info(m)
  }
}