package IO

import Models.{CipherCommand, CipherMode, Key, Message}

object CommandLine {

  def interpret(args: List[String]): CipherCommand = {
    val mode = if (args.head == "-d") CipherMode.Decrypt else CipherMode.Encrypt
    val k = Key(args(1))
    val m = Message(args.drop(2).mkString(" "))
    CipherCommand(m, k, mode)
  }

}
