object CaesarCipher extends Cipher {

  def convertCaesarKey(k: Key): Int = {
    val l = k.value.charAt(0).toLower
    val ascii = convertFromASCII(l, "lower")
    shift(ascii, convertKey)
  }

  def encrypt(m: Message, k: Key): Message = {
    val keyShift = convertCaesarKey(k)
    val result = m.value.map(c => {
      val cType = charType(c)
      val ascii = convertFromASCII(c, cType)
      val shifted = shift(ascii, encrypt, keyShift)
      if (cType == "non-ascii") c else convertToASCII(shifted, cType)
    } )
    Message(result.toString)
  }

  def decrypt(m: Message, k: Key): Message = {
    val keyShift = convertCaesarKey(k)
    val result = m.value.map(c => {
      val cType = charType(c)
      val ascii = convertFromASCII(c, cType)
      val shifted = shift(ascii, decrypt, keyShift)
      if (cType == "non-ascii") c else convertToASCII(shifted, cType)
    } )
    Message(result.toString)
  }


}
