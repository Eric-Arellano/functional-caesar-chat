package Cipher

import Models.{CipherCommand, CipherMode, Key, Message}

object Cipher extends Cipher {

  def convertMessage(command: CipherCommand): Message = command.mode match {
    case CipherMode.Encrypt => Cipher.encrypt(command.m, command.k)
    case CipherMode.Decrypt => Cipher.decrypt(command.m, command.k)
  }

  def encrypt(m: Message, k: Key): Message =
    if (k.value.length > 1) VigenereCipher.encrypt(m, k) else CaesarCipher.encrypt(m, k)

  def decrypt(m: Message, k: Key): Message =
    if (k.value.length > 1) VigenereCipher.decrypt(m, k) else CaesarCipher.decrypt(m, k)
}

protected trait Cipher {

  def encrypt(m: Message, k: Key): Message

  def decrypt(m: Message, k: Key): Message

  def encrypt(c: Char, shift: Int, alphabetSize: Int): Char =
    Math.floorMod(c + shift, alphabetSize).toChar

  def decrypt(c: Char, shift: Int, alphabetSize: Int): Char =
    Math.floorMod(c - shift, alphabetSize).toChar

  def convertKey(c: Char, shift: Int, alphabetSize: Int): Char =
    (Math.floorMod(c, alphabetSize) + 1).toChar

}
