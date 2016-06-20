package models

import java.util.Date
import play.api.Play.current
import slick.lifted.Tag
import java.sql.Timestamp
import slick.driver.PostgresDriver.api._

object DbTables {

//  class Coffees(tag: Tag)
//    extends Table[(String, Int, Double, Int, Int)](tag, "COFFEES") {
//
//    def name: Rep[String] = column[String]("COF_NAME", O.PrimaryKey)
//    def supID: Rep[Int] = column[Int]("SUP_ID")
//    def price: Rep[Double] = column[Double]("PRICE")
//    def sales: Rep[Int] = column[Int]("SALES")
//    def total: Rep[Int] = column[Int]("TOTAL")
//
//    def * : ProvenShape[(String, Int, Double, Int, Int)] =
//      (name, supID, price, sales, total)
//
//    // A reified foreign key relation that can be navigated to create a join
//    def supplier: ForeignKeyQuery[Suppliers, (Int, String, String, String, String, String)] =
//      foreignKey("SUP_FK", supID, TableQuery[Suppliers])(_.id)
//  }


  case class Instructor(id: Int, firstName: String, lastName: String)

  class InstructorT(tag: Tag) extends Table[Instructor](tag, "INSTRUCTOR") {
    def id = column[Int]("INSTRUCTOR_ID", O.PrimaryKey, O.AutoInc)
    def firstName = column[String]("INSTRUCTOR_FIRST_NAME")
    def lastName = column[String]("INSTRUCTOR_FIRST_NAME")
    def * : ProvenShape[(Int, String, String)] = (id, firstName, lastName)
  }

  val instructors = TableQuery[InstructorT]


  case class AbilityLevel(id: Int, name: String)

  class AbilityLevelT(tag: Tag) extends Table[AbilityLevel](tag, "ABILITY_LEVEL") {
    def id = column[Int]("ABILITY_LEVEL_ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("ABILITY_LEVEL_NAME")
    def * : ProvenShape[(Int, String)] = (id, name)
  }

  val abilityLevels = TableQuery[AbilityLevelT]


  case class Rider(id: Int, firstName: String, lastName: String, levelId: Int)

  class RiderT(tag: Tag) extends Table[Rider](tag, "RIDER") {
    def id = column[Int]("RIDER_ID", O.PrimaryKey, O.AutoInc)
    def firstName = column[String]("RIDER_FIRST_NAME")
    def lastName = column[String]("RIDER_FIRST_NAME")
    def levelId = column[Int]("ABILITY_LEVEL_ID")
    def * : ProvenShape[(Int, String, String, Int)] = (id, firstName, lastName, levelId)
    def abilityLevel = foreignKey("ABILITY_LEVEL_FK", levelId, abilityLevels)(_.id)
  }

  val riders = TableQuery[RiderT]


  case class Lesson(id: Int, levelId: Int, instructorId: Int)

  class LessonT(tag: Tag) extends Table[Lesson](tag, "LESSON") {
    def id = column[Int]("LESSON_ID", O.PrimaryKey, O.AutoInc)
    def levelId = column[Int]("ABILITY_LEVEL_ID")
    def instructorId = column[Int]("INSTRUCTOR_ID")
    def * : ProvenShape[(Int, Int, Int)] = (id, levelId, instructorId)
    def abilityLevel = foreignKey("ABILITY_LEVEL_FK", levelId, abilityLevels)(_.id)
    def instructor = foreignKey("INSTRUCTOR_FK", instructorId, instructors)(_.id)
  }

  val lessons = TableQuery[LessonT]


  case class Booking(id: Int, riderId: Int, lessonId: Int)

  class BookingT(tag: Tag) extends Table[Booking](tag, "BOOKING") {
    def id = column[Int]("BOOKING_ID", O.PrimaryKey, O.AutoInc)
    def riderId = column[Int]("BOOKING_RIDER")
    def lessonId = column[Int]("BOOKING_LESSON")
    def * : ProvenShape[(Int, Int, Int)] = (id, riderId, lessonId)
    def rider = foreignKey("RIDER_FK", riderId, riders)(_.id)
    def lesson = foreignKey("LESSON_FK", lessonId, lessons)(_.id)
  }

  val bookings = TableQuery[BookingT]

}