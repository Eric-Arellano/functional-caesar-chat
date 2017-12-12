import Cipher.Cipher
import IO.CommandLine
import Models.{Key, Message}
import org.scalatest._

class CommandLineSpec extends FlatSpec with Matchers {

  "The command line interpreter" should "choose between encryption and decryption via the first argument" in {
    val argsDecrypt = List("-d", "f", "hello")
    val messageDecrypt: Message = CommandLine.interpret(argsDecrypt)
    val cipherDecrypt: Message = Cipher.decrypt(Message("hello"), Key("f"))
    messageDecrypt should equal (cipherDecrypt)
    val argsEncrypt = List("-e", "f", "hello")
    val messageEncrypt: Message = CommandLine.interpret(argsEncrypt)
    val cipherEncrypt: Message = Cipher.encrypt(Message("hello"), Key("f"))
    messageEncrypt should equal (cipherEncrypt)
  }

  it should "pass the key to the Cipher and return different results in response" in {
    val args1 = List("-e", "f", "hello")
    val args2 = List("-e", "abc", "hello")
    val m1: Message = CommandLine.interpret(args1)
    val m2: Message = CommandLine.interpret(args2)
    m1 should not equal m2
  }

  it can "accept a message with spaces in-between" in {
    val args = List("-e", "f", "hello", "this", "is", "a", "test")
    val m: Message = CommandLine.interpret(args)
    val cipherMessage = Cipher.encrypt(Message("hello this is a test"), Key("f"))
    m should equal (cipherMessage)
  }
}
