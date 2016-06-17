package models


import scala.slick.driver.MySQLDriver.simple._


class DbTables {

  case class Instructor(id: Int, firstName: String, lastName: String)

  class InstructorT(tag: Tag) extends Table[Instructor](tag, "INSTRUCTOR") {

    def id = column[Int]("INSTRUCTOR_ID", O.PrimaryKey, O.AutoInc)
    def firstName = column[String]("INSTRUCTOR_FIRST_NAME")
    def lastName = column[String]("INSTRUCTOR_FIRST_NAME")

    def * = (id, firstName, lastName) <>((Instructor.apply _).tupled, Instructor.unapply)
  }

  val instructors = TableQuery[InstructorT]


  case class AbilityLevel(id: Int, name: String)

  class AbilityLevelT(tag: Tag) extends Table[AbilityLevel](tag, "ABILITY_LEVEL") {

    def id = column[Int]("ABILITY_LEVEL_ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("ABILITY_LEVEL_NAME")

    def * = (id, name) <>((AbilityLevel.apply _).tupled, AbilityLevel.unapply)
  }

  val abilityLevels = TableQuery[AbilityLevelT]


  case class Rider(id: Int, firstName: String, lastName: String, levelId: Int)

  class RiderT(tag: Tag) extends Table[Rider](tag, "RIDER") {

    def id = column[Int]("RIDER_ID", O.PrimaryKey, O.AutoInc)
    def firstName = column[String]("RIDER_FIRST_NAME")
    def lastName = column[String]("RIDER_FIRST_NAME")
    def levelId = column[Int]("ABILITY_LEVEL_ID")
    def * = (id, firstName, lastName, levelId) <>((Rider.apply _).tupled, Rider.unapply)

    def abilityLevel = foreignKey("ABILITY_LEVEL_FK", levelId, abilityLevels)(_.id)
  }

  val riders = TableQuery[RiderT]


  case class Lesson(id: Int, levelId: Int, instructorId: Int)

  class LessonT(tag: Tag) extends Table[Lesson](tag, "LESSON") {

    def id = column[Int]("LESSON_ID", O.PrimaryKey, O.AutoInc)
    def levelId = column[Int]("ABILITY_LEVEL_ID")
    def instructorId = column[Int]("INSTRUCTOR_ID")
    def * = (id, levelId, instructorId) <>((Lesson.apply _).tupled, Lesson.unapply)

    def abilityLevel = foreignKey("ABILITY_LEVEL_FK", levelId, abilityLevels)(_.id)
    def instructor = foreignKey("INSTRUCTOR_FK", instructorId, instructors)(_.id)
  }

  val lessons = TableQuery[LessonT]


  case class Booking(id: Int, riderId: Int, lessonId: Int)

  class BookingT(tag: Tag) extends Table[Booking](tag, "BOOKING") {

    def id = column[Int]("BOOKING_ID", O.PrimaryKey, O.AutoInc)
    def riderId = column[Int]("BOOKING_RIDER")
    def lessonId = column[Int]("BOOKING_LESSON")
    def * = (id, riderId, lessonId) <>((Booking.apply _).tupled, Booking.unapply)

    def rider = foreignKey("RIDER_FK", riderId, riders)(_.id)
    def lesson = foreignKey("LESSON_FK", lessonId, lessons)(_.id)
  }

  val bookings = TableQuery[BookingT]

}

object DbTables extends DbTables