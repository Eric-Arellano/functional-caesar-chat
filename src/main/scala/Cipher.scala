trait Cipher {

  def encrypt(m: Message, k: Key): Message
  def decrypt(m: Message, k: Key): Message

  private val lowerLetters = ('a' to 'z').toSet
  private val upperLetters = ('A' to 'Z').toSet
  private val AlphabetSize = 26

  val charType: (Char) => String =
    (c: Char) => if (lowerLetters.contains(c)) "lower" else if (upperLetters.contains(c)) "upper" else "non-ascii"
  private val asciiShiftAmount = (t: String) => if (t == "lower") 97 else if (t == "upper") 65 else 0

  val encrypt: (Char, Int) => Char =
    (c: Char, shift: Int) => Math.floorMod(c + shift, AlphabetSize).toChar
  val decrypt: (Char, Int) => Char =
    (c: Char, shift: Int) => Math.floorMod(c - shift, AlphabetSize).toChar
  val convertKey: (Char, Int) => Char =
    (c: Char, shift: Int) => (Math.floorMod(c, AlphabetSize) + 1).toChar

  def shift(c: Char, f: (Char, Int) => Char, shift: Int = 0): Char =
    f(c, shift)

  def convertFromASCII(c: Char, charType: String): Char = {
    val shift = asciiShiftAmount(charType)
    val int = c.toInt - shift
    int.toChar
  }

  def convertToASCII(c: Char, charType: String): Char = {
    val shift = asciiShiftAmount(charType)
    val int = c.toInt + shift
    int.toChar
  }

}
