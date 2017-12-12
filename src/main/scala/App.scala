import IO.CommandLine
import Models.Message

object App extends App {
  val m: Message = CommandLine.interpret(args.toList)
  println(m)
}
