package Akka

import IO.CipherMode
import Models.{Key, Message}
import akka.actor.{ActorRef, ActorSystem}


object ChatApp extends App {
  val system: ActorSystem = ActorSystem("chatApp")

  val printer: ActorRef = system.actorOf(PrinterActor.props, "printerActor")
  val cipher: ActorRef = system.actorOf(CipherActor.props(printer), "cipherActor")

  import CipherActor._

  cipher ! (Message("Hi Akka!"), Key("ab"), CipherMode.Encrypt)
  cipher ! Send

  cipher ! (Message("Ik Clmb!"), Key("ab"), CipherMode.Decrypt)
  cipher ! Send

}