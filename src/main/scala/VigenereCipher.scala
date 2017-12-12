object VigenereCipher extends Cipher with Ascii {

  def encrypt(m: Message, k: Key): Message = {
    val f = (m: Message, k: Key) => CaesarCipher.encrypt(m, k)
    convertMessage(m, k, f)
  }

  def decrypt(m: Message, k: Key): Message = {
    val f = (m: Message, k: Key) => CaesarCipher.decrypt(m, k)
    convertMessage(m, k, f)
  }

  private def convertMessage(m: Message, k: Key, caesarFunction: (Message, Key) => Message): Message = {
    var keyIndex = 0    // TODO: NOT FUNCTIONAL!
    val result = m.value.map(c => {
      val message = Message(c.toString)
      val (key, i) = getKey(k, keyIndex)
      keyIndex = i
      caesarFunction(message, key).value.charAt(0)
    })
    Message(result.toString)
  }

  private def getKey(k: Key, i: Int): (Key, Int) = {
    val key = Key(k.value(i).toString)
    if (i == k.value.length - 1)
      (key, 0)
    else
      (key, i + 1)
  }
}
