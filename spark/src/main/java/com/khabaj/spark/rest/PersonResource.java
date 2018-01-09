package com.khabaj.spark.rest;

public class PersonResource {

   /* private InMemoryPersonRepository personRepository;

    public PersonResource() {
        personRepository = InMemoryPersonRepository.getInstance();
    }

    public List<Person> getPersons(Request request, Response response) {
        response.type("application/json");
        return personRepository.getAll();
    }

    public Person addPerson(Request request, Response response) {
        response.type("application/json");
        Person person = new Gson().fromJson(request.body(), Person.class);
        personRepository.save(person);
        response.status(201);
        return person;
    }*/
}
