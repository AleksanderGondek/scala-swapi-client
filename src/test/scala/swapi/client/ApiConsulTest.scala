package scala.swapi.client

import com.softwaremill.sttp._
import com.softwaremill.sttp.testing.SttpBackendStub
import org.scalatest.FunSpec

class GetCharactersSpec extends FunSpec {
  val testConfig = Config(
    "https://swapi.xyz/api/",
    1,
    "output.json"
  )

  val validResponse: String = "{\"status\":\"ok\"}"

  trait SttpBackendMock extends SttpBackend {
    override implicit val backend = SttpBackendStub
      .synchronous
      .whenRequestMatches(
        _.uri.path.startsWith(
          List("api", "people")
        )
      )
      .thenRespondCyclicResponses(
        Response.ok[String](validResponse),
        Response.error[String]("Error", 500, "500 error")
      )
  }

  val getCharactersMethod = new getCharacters(
    this.testConfig
  ) with SttpBackendMock

  describe("getCharacters") {

    describe("method invocation") {

      it("should call apropriate endpoint and return proper response") {
        val response = getCharactersMethod()
        response match {
          case Some(responseBody: String) => assert(responseBody == validResponse)
          case None => assert(false)
        }
      }

      it("should return None on api error") {
        val response = getCharactersMethod()
        response match {
          case Some(_) => assert(false)
          case None => assert(true) 
        }
      }
    }
  }
}
