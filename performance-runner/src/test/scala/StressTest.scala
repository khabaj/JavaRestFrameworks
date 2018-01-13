package com.khabaj.perfrunner

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

abstract class StressTest extends Simulation {

  val serviceURL = sys.env.get("BASE_URL")
  val httpConf = http.baseURL(serviceURL.getOrElse("http://localhost:8090"))

  val getAllPersons = scenario("")
    .forever() {
      exec(http("Get All Persons Stress Test")
        .get("/persons")
        .header("Accept", "application/json")
        .check(status.is(200))
      )
    }

  setUp(
    getAllPersons.inject(rampUsers(20000) over(5 minutes))
  )
    .assertions(global.successfulRequests.percent.gte(90))
    .protocols(httpConf)
    .maxDuration(6 minutes)
}