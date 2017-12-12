package IO

import Models.{CipherCommand, CipherMode, Key, Message}

import scala.io.StdIn

object Console {

  def readCipherCommand(): CipherCommand = {
    output("Input 1 to decrypt or 2 to encrypt: ")
    val inputMode = readNumber(num => num == 1 || num == 2)
    val mode = if (inputMode == 1) CipherMode.Decrypt else CipherMode.Encrypt
    output("Input your key. This can only contain letters a-Z: ")
    val k: Key = Key(Console.readString(s => !s.isEmpty &&
      s.forall(c => c.isLetter && c <= 'z')))
    output("Input the message: ")
    val m: Message = Message(Console.readString())
    CipherCommand(m, k, mode)
  }

  def output(instr: String): Unit =
    println(instr)

  def readNumber(validation: Int => Boolean = _ => true): Int = {
    val i = StdIn.readInt()
    if (validation(i)) i else readNumber(validation)
  }

  def readString(validation: String => Boolean = _ => true): String = {
    val s = StdIn.readLine()
    if (validation(s)) s else readString(validation)
  }

}
