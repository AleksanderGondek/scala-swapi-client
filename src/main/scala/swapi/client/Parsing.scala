package scala.swapi.client

import com.typesafe.scalalogging.LazyLogging
import io.circe.Json
import  io.circe.parser._
import scala.util.Either

object Parsing extends LazyLogging {
    def fromJson(text: String): Option[Json] = {
        parse(text) match {
            case Right(json: Json) => {
                logger.debug(s"Deserialized json: $json")
                Some(json)
            }
            case Left(error: io.circe.ParsingFailure) => {
                logger.error(s"Unable to deserialize text from json: $error")
                None
            }
        }
    }
}
