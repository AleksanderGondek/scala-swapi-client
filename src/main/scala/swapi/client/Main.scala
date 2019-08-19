package scala.swapi.client

import com.typesafe.scalalogging.LazyLogging


object Main extends App with LazyLogging {
  logger.info("Starting up SW-Api client..")
  getConfig() match {
    case Some(conf: Config) => {
      logger.info(s"Loaded configuration file: $conf")
      new getCharacters(conf)()
    }
    case None => {
      logger.info("Configuration file not loaded, application will close")
    }
  }
  logger.info("SW-Api client shutting down..")
}
