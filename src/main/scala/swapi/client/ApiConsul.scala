package scala.swapi.client

import com.softwaremill.sttp._
import com.typesafe.scalalogging.LazyLogging
import scala.collection.immutable.Map;

trait SttpBackend {
    implicit val backend = HttpURLConnectionBackend()
}

class getCharacters(config: Config) extends SttpBackend with LazyLogging {
    private val requestHeaders: Map[String, String] = Map[String, String](
        "User-Agent" -> "Scala-swapi-client-test-app",
        "Content-Type" -> "application/json"
    )

    private def requestUri: Uri = {
        uri"${config.apiUri}people/?format=json"
    }

    private def getHttpRequest: Request[String,Nothing] = {
        sttp
        .headers(this.requestHeaders)
        .get(this.requestUri)
    }

    def apply(): Option[String] = {
        logger.info(s"Sending HTTP GET request: ${this.requestUri.toString}")
        this.getHttpRequest.send().body match {
            case Right(responseBody: String) => {
                logger.debug(s"HTTP GET response: ${responseBody}")
                Some(responseBody)
            }
            case Left(errorMsg: String) => {
                logger.error(s"Received non 2xx HTTP status: $errorMsg")
                None
            }
        }
    }
}