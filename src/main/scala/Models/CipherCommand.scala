package Models

sealed case class CipherCommand(m: Message, k: Key, mode: CipherMode.Value)
