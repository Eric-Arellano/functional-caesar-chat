import Akka.CipherActor
import Models.{CipherCommand, CipherMode, Key, Message}
import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}

class CipherActorSpec(_system: ActorSystem) extends TestKit(_system)
  with FlatSpecLike with Matchers with BeforeAndAfterAll with ImplicitSender {

  def this() = this(ActorSystem("CipherActorSpec"))

  override def afterAll: Unit = {
    shutdown(system)
  }

  "The Cipher Actor" should "return an encrypted/decrypted message to sender when passed a CipherCommand" in {
    val cipher = TestActorRef[CipherActor]
    val command = CipherCommand(Message("hi"), Key("a"), CipherMode.Encrypt)
    cipher ! command
    expectMsg(Message("ij"))
  }

}
