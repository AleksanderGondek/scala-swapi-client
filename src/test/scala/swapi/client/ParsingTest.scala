package scala.swapi.client

import org.scalatest.FunSpec

class ParsingSpec extends FunSpec {
    describe("Parsing") {

        describe("fromJson method") {

            it("should return deserialized object from valid JSON") {
                val validJson: String = """
                {
                  "status": "ok"
                }
                """
                Parsing.fromJson(validJson) match {
                    case Some(x) => {
                        val cursor = x.hcursor
                        assert(cursor.get[String]("status").getOrElse("Not a value") == "ok")
                    }
                    case None => assert(false)
                }
            }

            it("should return None for attempt to deserialize invalid JSON text") {
                Parsing.fromJson("asdfasdf") match {
                    case Some(x) => assert(false)
                    case None => assert(true)
                }
            }
        }
    }
}
