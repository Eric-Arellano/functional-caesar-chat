import Cipher.Cipher
import Models.{Key, Message}

object App extends App {
  println(Cipher.encrypt(Message("Hello Eric!"), Key("ab")))
  println(Cipher.decrypt(Message("Igmnp Ftje!"), Key("ab")))
}
