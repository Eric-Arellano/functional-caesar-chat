import Cipher.CaesarCipher
import Models.{Key, Message}
import org.scalatest._

class CaesarCipherSpec extends FlatSpec with Matchers {

  private def encryptThenDecrypt(m: Message, k: Key = Key("z")): Message = {
    val e: Message = CaesarCipher.encrypt(m, k)
    CaesarCipher.decrypt(e, k)
  }

  "The Caesar Cipher.Cipher" should "decrypt what it encrypts when using the same key" in {
    val original = Message("hello")
    val d = encryptThenDecrypt(original)
    d should equal (original)
  }

  it should "not decrypt what it encrypts when using a different key" in {
    val original = Message("hello")
    val e = CaesarCipher.encrypt(original, Key("b"))
    val d = CaesarCipher.decrypt(Message(e.value), Key("c"))
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
