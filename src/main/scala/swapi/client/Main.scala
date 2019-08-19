package scala.swapi.client

trait ConsoleOutput {
  def println(txt: String) = Console.println(txt)
}

class HelloWorld extends ConsoleOutput {
  def greet() = this.println("Hello, World!")
}

object Main extends App {
  new HelloWorld().greet()
}
