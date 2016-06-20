package controllers

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import slick.driver.PostgresDriver.simple._
import models.DbTables._


object MyApp extends App {
  val query = TableQuery[Instructor]
  val db = Database.forConfig("pg-postgres")
  try{
    Await.result(db.run(DBIO.seq(
      query.result.map(println)
    )), Duration.Inf)
  } finally db.close
}