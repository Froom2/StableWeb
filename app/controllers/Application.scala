package controllers

import play.api.mvc._

import scala.slick.driver.PostgresDriver.simple._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Yey database!"))
  }

  val connectionUrl = "jdbc:postgresql://localhost/stepneywebdb?user=postgres&password=password"

//  // this is a class that represents the table I've created in the database
//  class Instructors(tag: Tag) extends Table[(String)](tag, "instructor") {
//    def name = column[String]("name")
//
//    def * = name
//  }
//  def listInstructors: List[String] = {
//
//    Database.forURL(connectionUrl, driver = "org.postgresql.Driver") withSession {
//      implicit session =>
//        val instructors = TableQuery[Instructors]
//
//        instructors.list
//
//    }
//  }

}

object Application extends Application