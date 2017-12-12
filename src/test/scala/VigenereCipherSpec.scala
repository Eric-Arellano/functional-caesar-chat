import Cipher.Cipher
import Models.{Key, Message}
import org.scalatest._

class VigenereCipherSpec extends FlatSpec with Matchers {

  private def encryptThenDecrypt(m: Message, k: Key = Key("zac")): Message = {
    val e: Message = Cipher.encrypt(m, k)
    Cipher.decrypt(e, k)
  }

  "The Vigenere Cipher" should "decrypt what it encrypts when using the same key" in {
    val original = Message("hello")
    val d = encryptThenDecrypt(original)
    d should equal (original)
  }

  it should "not decrypt what it encrypts when using a different key" in {
    val original = Message("hello")
    val e = Cipher.encrypt(original, Key("zac"))
    val d = Cipher.decrypt(Message(e.value), Key("cza"))
    d should not equal original
  }

  it should "allow a key that's longer than the message" in {
    val original = Message("hello")
    val d = encryptThenDecrypt(original, k = Key("zacbafeafd"))
    d should equal (original)
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
