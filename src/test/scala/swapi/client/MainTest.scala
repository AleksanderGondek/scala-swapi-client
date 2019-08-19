package scala.swapi.client

import org.scalatest.FunSpec

class HelloWordSpec extends FunSpec {
  trait ConsoleOutputMock extends ConsoleOutput {
      var messages: List[String] = List()

      override def println(txt: String) = {
        this.messages = txt :: this.messages
      }
  }
  
  val helloWord = new HelloWorld with ConsoleOutputMock
  
  describe("HelloWord class instance") {
    describe("Greet method") {
      it("should print 'Hello, World!' when called") {
        helloWord.greet()
        assert(helloWord.messages.contains("Hello, World!"))
      }
    }
  }
}
