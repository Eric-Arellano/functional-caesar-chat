package Akka

import akka.actor.{ActorRef, ActorSystem}

object ChatApp extends App {
  val system: ActorSystem = ActorSystem("chatApp")

  val console: ActorRef = system.actorOf(ConsoleActor.props, "consoleActor")
  val cipher: ActorRef = system.actorOf(CipherActor.props, "cipherActor")
  val supervisor: ActorRef = system.actorOf(SupervisorActor.props(cipher, console), "supervisorActor")

  import SupervisorActor._

  supervisor ! Start

}
