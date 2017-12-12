package Cipher

import Models.{Key, Message}
import scalaz._, syntax.std.list._

object VigenereCipher extends Cipher with Ascii {

  def encrypt(m: Message, k: Key): Message = {
    val f = (m: Message, k: Key) => CaesarCipher.encrypt(m, k)
    convertMessage(m, k, f)
  }

  def decrypt(m: Message, k: Key): Message = {
    val f = (m: Message, k: Key) => CaesarCipher.decrypt(m, k)
    convertMessage(m, k, f)
  }

  private def convertMessage(message: Message, key: Key, caesarFunction: (Message, Key) => Message): Message = {
    val initialKeyIndex = 0
    val result: String = message.value.toList.mapAccumLeft(initialKeyIndex, (keyIndex: Int, c: Char) => {
      val newIndex = getNewKeyIndex(keyIndex, key)
      val k = Key(key.value(keyIndex).toString)
      val m = Message(c.toString)
      val r = caesarFunction(m, k).value.charAt(0)
      (newIndex, r)
    })._2.mkString("")
    Message(result)
  }

  private def getNewKeyIndex(i: Int, k: Key): Int =
    if (i == k.value.length - 1) 0 else i + 1
}
