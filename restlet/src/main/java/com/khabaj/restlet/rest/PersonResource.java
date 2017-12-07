package com.khabaj.restlet.rest;

import com.khabaj.common.domain.Person;
import com.khabaj.common.repository.InMemoryPersonRepository;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;


public class PersonResource extends ServerResource {

    InMemoryPersonRepository personRepository;

    public PersonResource() {
        this.personRepository = InMemoryPersonRepository.getInstance();
    }

    @Get()
    public Person getPerson() {
        Integer personId = Integer.valueOf(getAttribute("id"));
        return personRepository.getById(personId).orElseGet(() -> {
            getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
            return null;
        });
    }

    @Put
    public void updatePerson(Person person) {
        Integer personId = Integer.valueOf(getAttribute("id"));
        if (person.getId() == null)
            person.setId(personId);

        personRepository.update(person);
    }

    @Delete
    public void deletePerson() {
        Integer personId = Integer.valueOf(getAttribute("id"));
        personRepository.delete(personId);
        getResponse().setStatus(Status.SUCCESS_NO_CONTENT);
    }
}
