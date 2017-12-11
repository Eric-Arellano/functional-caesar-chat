import org.scalatest._

class CaesarCipherSpec extends FlatSpec with Matchers {

  "The Caesar Cipher" should "decrypt what it encrypts when using the same key" in {
    val original = Message("hello")
    val e: Message = CaesarCipher.encrypt(original, Key("b"))
    val d: Message = CaesarCipher.decrypt(Message(e.value), Key("b"))
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
    val e = CaesarCipher.encrypt(original, Key("b"))
    val d = CaesarCipher.decrypt(Message(e.value), Key("b"))
    d should equal (original)
  }

  it can "support spaces" in {
    val original = Message("hello eric")
    val e = CaesarCipher.encrypt(original, Key("b"))
    val d = CaesarCipher.decrypt(Message(e.value), Key("b"))
    d should equal (original)
  }

  it can "support non-letters" in {
    val original = Message("مرحبا")
    val e = CaesarCipher.encrypt(original, Key("b"))
    val d = CaesarCipher.decrypt(Message(e.value), Key("b"))
    d should equal (original)
  }

}
