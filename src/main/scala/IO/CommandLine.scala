package IO

import Cipher.Cipher
import Models.{Message, Key}

object CommandLine {

  def interpret(args: List[String]): Message = {
    val mode = if (args.head == "-d") CipherMode.Decrypt else CipherMode.Encrypt
    val k = Key(args(1))
    val m = Message(args.drop(2).mkString(" "))
    mode match {
      case CipherMode.Decrypt => Cipher.decrypt(m, k)
      case CipherMode.Encrypt => Cipher.encrypt(m, k)
    }
  }

}
