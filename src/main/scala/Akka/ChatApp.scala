package Akka

import akka.actor.{ActorRef, ActorSystem}

object ChatApp extends App {

  val system: ActorSystem = ActorSystem("chatApp")

  val console: ActorRef = system.actorOf(ConsoleActor.props, "consoleActor")
  val cipher: ActorRef = system.actorOf(CipherActor.props, "cipherActor")
  val master: ActorRef = system.actorOf(MasterActor.props(cipher, console, system), "masterActor")

  import MasterActor._

  master ! Start

}
