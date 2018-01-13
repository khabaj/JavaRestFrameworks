package com.khabaj.perfrunner

import io.gatling.core.Predef._

import scala.concurrent.duration._

class Simulation10Users extends BaseSimulation {

  setUp(
    getAllPersons.inject(atOnceUsers(5))
  )
}