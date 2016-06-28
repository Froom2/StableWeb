package models

import models.DbTables.{BookingT, Booking}
import slick.driver.PostgresDriver.api._
import slick.lifted

//import slick.driver.PostgresDriver.api._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.db.DB
import play.api.Play.current

trait DAOComponent {

  def insert(booking: Booking): Future[Int]
  def update(id: Int, booking: Booking): Future[Int]
  def delete(id: Int): Future[Int]
//  def list(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): Future[Page[Employee]]
  def findById(id: Int): Future[Booking]
  def count: Future[Int]

}

object DAO extends DAOComponent {

  private val bookings: lifted.TableQuery[BookingT] = TableQuery[BookingT]

  private def db: Database = Database.forDataSource(DB.getDataSource())

  /**
    * Filter booking with id
    */
  private def filterQuery(id: Int): Query[BookingT, Booking, Seq] =
    bookings.filter(_.id === id)
//
//  /**
//    * Count employees with a filter
//    */
//  private def count(filter: String): Future[Int] =
//    try db.run(employees.filter(_.name.toLowerCase like filter.toLowerCase()).length.result)
//    finally db.close

  /**
    * Count total employees in database
    */
  override def count: Future[Int] =
    try db.run(bookings.length.result)
    finally db.close

  /**
    * Find employee by id
    */
  override def findById(id: Int): Future[Booking] =
    try db.run(filterQuery(id).result.head)
    finally db.close

  /**
    * Create a new employee
    */
  override def insert(booking: Booking): Future[Int] =
    try db.run(bookings += booking)
    finally db.close

  /**
    * Update employee with id
    */
  override def update(id: Int, booking: Booking): Future[Int] =
    try db.run(filterQuery(id).update(booking))
    finally db.close

  /**
    * Delete employee with id
    */
  override def delete(id: Int): Future[Int] =
    try db.run(filterQuery(id).delete)
    finally db.close
//
//  /**
//    * Return a page of employees
//    */
//  override def list(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): Future[Page[Employee]] = {
//    try {
//      val offset = pageSize * page
//      val query =
//        (for {
//          employee <- employees if employee.name.toLowerCase like filter.toLowerCase
//        } yield (employee)).drop(offset).take(pageSize)
//      val totalRows = count(filter)
//      val result = db.run(query.result)
//      result flatMap (employees => totalRows map (rows => Page(employees, page, offset, rows)))
//    } finally { db.close() }
//  }

}
