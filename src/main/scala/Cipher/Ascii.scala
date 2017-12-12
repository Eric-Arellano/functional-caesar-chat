package Cipher

trait Ascii {

  private val lowerLetters = ('a' to 'z').toSet
  private val upperLetters = ('A' to 'Z').toSet
  final val AlphabetSize = 26

  object CharType extends Enumeration {
    val LowerCase, UpperCase, NonASCII = Value
  }

  val charType: (Char) => CharType.Value = (c: Char) => {
    if (lowerLetters.contains(c))
      CharType.LowerCase
    else if (upperLetters.contains(c))
      CharType.UpperCase
    else
      CharType.NonASCII
  }

  def shift(c: Char, shiftFunction: (Char, Int, Int) => Char, shift: Int = 0): Char =
    shiftFunction(c, shift, AlphabetSize)

  def convertFromASCII(c: Char, t: CharType.Value): Char = {
    val shift = asciiShiftAmount(t)
    val int = c.toInt - shift
    int.toChar
  }

  def convertToASCII(c: Char, t: CharType.Value): Char = {
    val shift = asciiShiftAmount(t)
    val int = c.toInt + shift
    int.toChar
  }

  private val asciiShiftAmount = (t: CharType.Value) => {
    if (t == CharType.LowerCase) 97
    else if (t == CharType.UpperCase) 65
    else 0
  }

}
