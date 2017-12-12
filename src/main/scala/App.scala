import Cipher.Cipher
import IO.Console

object App extends App {
//  val m: Message = CommandLine.interpret(args.toList)
//  println(m)

  val command = Console.readCipherCommand()
  val m = Cipher.convertMessage(command)
  println(m)
}
