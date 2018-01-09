package com.khabaj.spark;

import com.google.gson.Gson;
import com.khabaj.common.domain.Person;
import com.khabaj.common.repository.InMemoryPersonRepository;
import com.khabaj.spark.rest.PersonResource;

import static spark.Spark.*;

public class Application {

    public static void main(String[] args) {
        port(8090);

        Gson gson = new Gson();
        PersonResource personResource = new PersonResource();
        InMemoryPersonRepository personRepository = InMemoryPersonRepository.getInstance();

        path("/persons", () -> {
            get("", "application/json", (request, response) -> {
                response.type("application/json");
                return personRepository.getAll();
            }, gson::toJson);
            post("", "application/json", (request, response) -> {
                response.type("application/json");
                Person person = new Gson().fromJson(request.body(), Person.class);
                personRepository.save(person);
                response.status(201);
                return person;
            }, gson::toJson);
        });
    }
}
