import Cipher.Cipher
import Models.{Key, Message}
import org.scalatest._

class CaesarCipherSpec extends FlatSpec with Matchers {

  private def encryptThenDecrypt(m: Message, k: Key = Key("z")): Message = {
    val e: Message = Cipher.encrypt(m, k)
    Cipher.decrypt(e, k)
  }

  "The Caesar Cipher" should "encrypt 'hello' with key 'b' to be 'jgnnq" in {
    val e = Cipher.encrypt(Message("hello"), Key("b")).value
    e should equal("jgnnq")
  }

  it should "decrypt what it encrypts when using the same key" in {
    val original = Message("hello")
    val d = encryptThenDecrypt(original)
    d should equal(original)
  }

  it should "not decrypt what it encrypts when using a different key" in {
    val original = Message("hello")
    val e = Cipher.encrypt(original, Key("b"))
    val d = Cipher.decrypt(Message(e.value), Key("c"))
    d should not equal original
  }

  it can "support upper-case & mixed-case" in {
    val original = Message("HeLlO")
    val d = encryptThenDecrypt(original)
    d should equal (original)
  }

  it can "support spaces" in {
    val original = Message("hello eric")
    val d = encryptThenDecrypt(original)
    d should equal (original)
  }

  it can "support non-letters" in {
    val original = Message("مرحبا")
    val d = encryptThenDecrypt(original)
    d should equal (original)
  }

}
