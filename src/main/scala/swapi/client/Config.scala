package scala.swapi.client

import com.typesafe.scalalogging.LazyLogging
import pureconfig.error.ConfigReaderFailures
import pureconfig.generic.auto._
import scala.util.Either

case class Config(
    apiUri: String,
    filmId: Int,
    outputPath: String
)

object getConfig extends LazyLogging {
    def apply(): Option[Config] = {
        pureconfig.loadConfig[Config]("scala.swapi.client") match {
            case Right(conf: Config) => Some(conf)
            case Left(error: ConfigReaderFailures) => {
                logger.error(s"Unable to load config file. Error details: $error")
                None
            }
            case _ => {
                logger.error(s"Unexpected error while loading application config file")
                None
            }
        }
    }
}
