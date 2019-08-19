package scala.swapi.client

import com.typesafe.scalalogging.LazyLogging

trait ConsoleOutput {
  def println(txt: String) = Console.println(txt)
}

class HelloWorld extends ConsoleOutput {
  def greet() = this.println("Hello, World!")
}

object Main extends App with LazyLogging {
  logger.info("Starting up SW-Api client..")
  getConfig() match {
    case Some(conf: Config) => {
      logger.info(s"Loaded configuration file: $conf")
      new HelloWorld().greet()
    }
    case None => {
      logger.info("Configuration file not loaded, application will close")
    }
  }
  logger.info("SW-Api client shutting down..")
}
