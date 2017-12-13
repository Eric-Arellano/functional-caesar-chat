import IO.CommandLine
import Models.{CipherMode, Key, Message}
import org.scalatest._

class CommandLineSpec extends FlatSpec with Matchers {

  "The command line interpreter" should "choose between encryption and decryption via the first argument" in {
    val argsDecrypt = List("-d", "f", "hello")
    val decryptMode = CommandLine.interpret(argsDecrypt).mode
    decryptMode should equal(CipherMode.Decrypt)
    val argsEncrypt = List("-e", "f", "hello")
    val encryptMode = CommandLine.interpret(argsEncrypt).mode
    encryptMode should equal(CipherMode.Encrypt)
  }

  it should "pass the key to the Cipher" in {
    val args1 = List("-e", "f", "hello")
    val args2 = List("-e", "abc", "hello")
    val k1: Key = CommandLine.interpret(args1).k
    val k2: Key = CommandLine.interpret(args2).k
    k1 should not equal k2
  }

  it can "accept a message with spaces in-between" in {
    val args = List("-e", "f", "hello", "this", "is", "a", "test")
    val m: Message = CommandLine.interpret(args).m
    m should equal(Message("hello this is a test"))
  }
}
