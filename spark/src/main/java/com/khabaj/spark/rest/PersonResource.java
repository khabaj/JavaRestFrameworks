package com.khabaj.spark.rest;

import com.google.gson.Gson;
import com.khabaj.common.domain.Person;
import com.khabaj.common.repository.InMemoryPersonRepository;
import spark.ResponseTransformer;

import java.util.Optional;

import static spark.Spark.*;


public class PersonResource {

    public PersonResource() {
        final InMemoryPersonRepository personRepository = InMemoryPersonRepository.getInstance();

        get("/persons", (request, response) -> {
            response.type("application/json");
            return personRepository.getAll();
        }, JsonUtil.json());

        get("/persons/:id", (request, response) -> {
            response.type("application/json");
            Optional<Person> person = personRepository.getById(Integer.valueOf(request.params(":id")));
            return person.orElseGet(() -> {
                response.status(404);
                return null;
            });
        }, JsonUtil.json());

        post("/persons", (request, response) -> {
            response.type("application/json");

            Person person = new Gson().fromJson(request.body(), Person.class);
            personRepository.save(person);
            response.status(201);
            return person;
        }, JsonUtil.json());

        put("/persons/:id", (request, response) -> {
            response.type("application/json");
            Person person = new Gson().fromJson(request.body(), Person.class);

            if (person.getId() == null)
                person.setId(Integer.valueOf(request.params(":id")));

            personRepository.update(person);
            return person;
        }, JsonUtil.json());

        delete("/persons/:id", (request, response) -> {
            personRepository.delete(Integer.valueOf(request.params(":id")));
            response.status(204);
            return "";
        });
    }

    static class JsonUtil {
        static String toJson(Object object) {
            return new Gson().toJson(object);
        }

        static ResponseTransformer json() {
            return JsonUtil::toJson;
        }
    }
}
