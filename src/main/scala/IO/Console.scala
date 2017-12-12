package IO

import Cipher.Cipher
import Models.{Key, Message}

import scala.io.StdIn

case class Instruction(value: String) extends AnyVal

object Console {

  def instruct(instr: Instruction): Unit =
    println(instr.value)

  def readNumber(validation: Int => Boolean = _ => true): Int = {
    val i = StdIn.readInt()
    if (validation(i)) i else readNumber(validation)
  }

  def readString(validation: String => Boolean = _ => true): String = {
    val s = StdIn.readLine()
    if (validation(s)) s else readString(validation)
  }

  def consoleApp(): Message = {
    Console.instruct(Instruction("Input 1 to decrypt or 2 to encrypt: "))
    val inputMode = Console.readNumber(num => num == 1 || num == 2)
    val mode = if (inputMode == 1) CipherMode.Decrypt else CipherMode.Encrypt
    Console.instruct(Instruction("Input your key. This can only contain letters a-Z: "))
    val k: Key = Key(Console.readString(s => s.forall(c => c.isLetter && c <= 'z')))
    Console.instruct(Instruction("Input the message: "))
    val m: Message = Message(Console.readString())
    mode match {
      case CipherMode.Decrypt => Cipher.decrypt(m, k)
      case CipherMode.Encrypt => Cipher.encrypt(m, k)
    }
  }

}
