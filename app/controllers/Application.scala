package controllers

import play.api.mvc._
import views.html.index
import scala.slick.driver.PostgresDriver.simple._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }




    // this is a class that represents the table I've created in the database
    class Instructors(tag: Tag) extends Table[(String)](tag, "instructor") {
      def name = column[String]("name")
      def * = name
    }

  val connectionUrl = "jdbc:postgresql://localhost/stepneywebdb?user=postgres&password=password"

  def listInstructors: List[String] = {

      Database.forURL(connectionUrl, driver = "org.postgresql.Driver") withSession {
        implicit session =>
          val instructors = TableQuery[Instructors]

          instructors.list

//          // SELECT * FROM users WHERE username='john'
//          instructors.filter(_.name === "Freya").list foreach { row =>
//            println("user whose username is 'Freya' has name " + row)
//          }
      }
  }


}

object Application extends Application