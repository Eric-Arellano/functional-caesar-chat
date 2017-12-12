package Cipher

import Models.{Key, Message}

object Cipher extends Cipher {

  def encrypt(m: Message, k: Key): Message =
    if (k.value.length > 1) VigenereCipher.encrypt(m, k) else CaesarCipher.encrypt(m, k)

  def decrypt(m: Message, k: Key): Message =
    if (k.value.length > 1) VigenereCipher.decrypt(m, k) else CaesarCipher.decrypt(m, k)
}

trait Cipher {

  def encrypt(m: Message, k: Key): Message

  def decrypt(m: Message, k: Key): Message

  val encrypt: (Char, Int, Int) => Char =
    (c: Char, shift: Int, alphabetSize: Int) => Math.floorMod(c + shift, alphabetSize).toChar
  val decrypt: (Char, Int, Int) => Char =
    (c: Char, shift: Int, alphabetSize: Int) => Math.floorMod(c - shift, alphabetSize).toChar
  val convertKey: (Char, Int, Int) => Char =
    (c: Char, shift: Int, alphabetSize: Int) => (Math.floorMod(c, alphabetSize) + 1).toChar

}
