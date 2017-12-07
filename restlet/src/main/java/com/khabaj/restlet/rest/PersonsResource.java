package com.khabaj.restlet.rest;

import com.khabaj.common.domain.Person;
import com.khabaj.common.repository.InMemoryPersonRepository;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.List;

public class PersonsResource extends ServerResource {

    InMemoryPersonRepository personRepository;

    public PersonsResource() {
        this.personRepository = InMemoryPersonRepository.getInstance();
    }

    @Get
    public List<Person> getPersons() {
        return personRepository.getAll();
    }

    @Post
    public void addPerson(Person person) {
        personRepository.save(person);
    }
}
