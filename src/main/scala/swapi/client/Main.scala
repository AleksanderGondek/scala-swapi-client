package scala.swapi.client

import com.typesafe.scalalogging.LazyLogging

trait ConsoleOutput {
  def println(txt: String) = Console.println(txt)
}

class HelloWorld extends ConsoleOutput {
  def greet() = this.println("Hello, World!")
}

object Main extends App with LazyLogging {
  logger.info("Starting up SWApi client..")
  new HelloWorld().greet()
  logger.info("SWApi client shutting down..")
}
