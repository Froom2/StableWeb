import controllers.Application
import org.scalatest.{Matchers, WordSpec}
import play.api.mvc.{AnyContent, Request, Result}
import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.concurrent.Future


class ApplicationSpec extends WordSpec with Matchers {

  def withCallToGet(ctrl: Application,
                    request: Request[AnyContent] = FakeRequest(method = "GET", ""))
                   (handler: Future[Result] => Any) = {
    handler(ctrl.index.apply(request))
  }

  "Application controller" must {
    "respond with OK" when {
      "a valid GET request is made" in {
        withCallToGet(Application) { result =>
          status(result) shouldBe OK
          contentAsString(result) should include("Hello!")
        }
      }
    }
  }
}
