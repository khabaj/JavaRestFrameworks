package com.khabaj.perfrunner

import io.gatling.core.Predef._

class Simulation100Users extends BaseSimulation {

  setUp(
    getAllPersons.inject(atOnceUsers(100))
  )
}