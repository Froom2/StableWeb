import java.text.SimpleDateFormat
import models.DbTables.Booking
import play.api._
import models._
import scala.concurrent.ExecutionContext.Implicits.global

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    StartData.insert()
  }

}

/**
  *  Starting set of data to be inserted into the sample application.
  */
object StartData {

  val sdf = new SimpleDateFormat("MM/dd/yyyy")

  def insert(): Unit = {
    DAO.count map { size =>
      if (size == 0) {
        val bookings = Seq(
          Booking(1,1,1),
          Booking(2,1,2),
          Booking(3,2,1),
          Booking(4,3,1),
          Booking(5,3,2)
        )
        bookings.map(DAO.insert)
      }
    }
  }
}
