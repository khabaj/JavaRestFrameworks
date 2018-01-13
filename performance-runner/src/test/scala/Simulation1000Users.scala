package com.khabaj.perfrunner

import io.gatling.core.Predef._

class Simulation1000Users extends BaseSimulation {

  setUp(
    getAllPersons.inject(atOnceUsers(1000))
  )
}