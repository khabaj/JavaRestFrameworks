package com.khabaj.perfrunner

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class GetAllPersonsSimulation extends Simulation {

  //val serviceUri = System.getenv("BASE_URL");
  val serviceUri = "http://localhost:8090";
  val httpConf = http.baseURL(serviceUri).warmUp(serviceUri)

  val getAllPersons = scenario("Get All Persons")
    .during(10 seconds) {
      exec(http("Get All Persons")
        .get("/persons")
        .header("Accept", "application/json")
        .check(status.is(200))
      )
    }


  setUp(
    getAllPersons.inject(atOnceUsers(5000)),

    //getAllPersons123.inject(constantUsersPerSec(500) during (30 seconds)),


  ).assertions(global.successfulRequests.percent.gte(90))
    .protocols(httpConf)
    .maxDuration(5 minutes)
}