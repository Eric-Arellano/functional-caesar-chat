package IO

import Cipher.Cipher
import Models.{Message, Key}

object CommandLine {

  def interpret(args: List[String]): Message = {
    val mode = if (args.head == "-d") CipherMode.Decrypt else CipherMode.Encrypt
    val k = Key(args(1))
    val m = Message(args.drop(2).mkString(" "))
    if (mode == CipherMode.Decrypt) Cipher.decrypt(m, k) else Cipher.encrypt(m, k)
  }

}
