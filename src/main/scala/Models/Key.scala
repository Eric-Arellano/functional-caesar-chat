package Models

case class Key(value: String) {
  require(!value.isEmpty, "Key cannot be empty.")
  require(value.forall(c => c.isLetter && c <= 'z'), "Key can only contain normal a-Z letters.")
}
