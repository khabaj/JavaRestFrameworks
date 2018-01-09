package com.khabaj.perfrunner

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class GetAllPersonsSimulation extends Simulation {

  //private val serviceUri = System.getenv("BASE_URL");
  private val serviceUri = "http://localhost:8090";


  val httpConf = http.baseURL(serviceUri)

  val getAllPersons = scenario("Get All Persons")
    .repeat(10) {
      exec(http("Get All Persons")
        .get("/persons")
        .header("Accept", "application/json")
        .check(status.is(200))
      )
    }

  setUp(
    getAllPersons.inject(atOnceUsers(30))
    //getAllPersons2.inject(atOnceUsers(100))
  ).assertions(global.successfulRequests.percent.gte(90))
    .protocols(httpConf)
    .maxDuration(10 minutes)
}