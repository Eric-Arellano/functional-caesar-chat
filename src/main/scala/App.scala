import IO.Console
import Models.Message

object App extends App {
//  val m: Message = CommandLine.interpret(args.toList)
//  println(m)

  val m: Message = Console.consoleApp()
  println(m)
}
