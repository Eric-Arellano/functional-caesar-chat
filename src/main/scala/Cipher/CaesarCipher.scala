package Cipher

import Models.{Key, Message}

protected object CaesarCipher extends Cipher with Ascii {

  def encrypt(m: Message, k: Key): Message = convertMessage(m, k, encrypt)

  def decrypt(m: Message, k: Key): Message = convertMessage(m, k, decrypt)

  private def convertMessage(m: Message, k: Key, shiftFunction: (Char, Int, Int) => Char): Message = {
    val keyShift = convertKey(k)
    val result = m.value.map(c => {
      val t = charType(c)
      val ascii = convertFromASCII(c, t)
      val shifted = shift(ascii, shiftFunction, keyShift)
      if (t == CharType.NonASCII) c else convertToASCII(shifted, t)
    })
    Message(result.toString)
  }

  private def convertKey(k: Key): Int = {
    val l = k.value.charAt(0).toLower
    val ascii = convertFromASCII(l, CharType.LowerCase)
    shift(ascii, convertKey)
  }

}
