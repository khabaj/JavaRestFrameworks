package com.khabaj.perfrunner

import io.gatling.core.Predef._

import scala.concurrent.duration._

class GetAllPersonsSimulation2 extends BaseSimulation {

  setUp(
    getAllPersons.inject(atOnceUsers(5))
  ),

  setUp(
    getAllPersons.inject(atOnceUsers(500))
  )

}