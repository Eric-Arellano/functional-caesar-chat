package IO

import Cipher.Cipher
import Models.{CipherCommand, CipherMode, Key, Message}

import scala.io.StdIn

object Console {

  def instruct(instr: String): Unit =
    println(instr)

  def readNumber(validation: Int => Boolean = _ => true): Int = {
    val i = StdIn.readInt()
    if (validation(i)) i else readNumber(validation)
  }

  def readString(validation: String => Boolean = _ => true): String = {
    val s = StdIn.readLine()
    if (validation(s)) s else readString(validation)
  }

  def readInputs(): CipherCommand = {
    instruct("Input 1 to decrypt or 2 to encrypt: ")
    val inputMode = readNumber(num => num == 1 || num == 2)
    val mode = if (inputMode == 1) CipherMode.Decrypt else CipherMode.Encrypt
    instruct("Input your key. This can only contain letters a-Z: ")
    val k: Key = Key(Console.readString(s => s.forall(c => c.isLetter && c <= 'z')))
    instruct("Input the message: ")
    val m: Message = Message(Console.readString())
    CipherCommand(m, k, mode)
  }

  def consoleApp(): Message = {
    val command = readInputs()
    command.mode match {
      case CipherMode.Decrypt => Cipher.decrypt(command.m, command.k)
      case CipherMode.Encrypt => Cipher.encrypt(command.m, command.k)
    }
  }

}
