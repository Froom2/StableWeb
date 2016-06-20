//package controllers
//
//import models.DbTables._
//import play.api.mvc._
//import models.DbTables._
//import scala.concurrent.Future
//import scala.slick.lifted.{Column, Query, TableQuery}
//import slick.jdbc.JdbcBackend._
//import slick.backend.DatabasePublisher
//import slick.driver.PostgresDriver._
//
//
//class Application extends Controller {
//
//  val db = Database.forConfig("database")
//
//  val setupAction: DBIO[Unit] = DBIO.seq(
//    // Create the schema by combining the DDLs for the Suppliers and Coffees
//    // tables using the query interfaces
//    (instructors.schema ++ riders.schema).create,
//
//    // Insert some suppliers
//    suppliers += (101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
//    suppliers += ( 49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
//    suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")
//  )
//
//  val setupFuture: Future[Unit] = db.run(setupAction)
//
//
//  def index = Action {
//    val name = listInstructors().head.firstName
//    Ok(views.html.index("Yey database!", name))
//  }
//
//
//
//
//}
//
//object Application extends Application