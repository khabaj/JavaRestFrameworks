package com.khabaj.resources;

import com.khabaj.common.domain.Person;
import com.khabaj.common.repository.InMemoryPersonRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    InMemoryPersonRepository personRepository;

    public PersonResource(InMemoryPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GET
    public List<Person> getPersons() {
        return personRepository.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") Integer personId) {

        Optional<Person> person = personRepository.getById(personId);
        return person
                .map(p -> Response.status(200).entity(p).build())
                .orElse(Response.status(404).build());
    }

    @POST
    public Response addPerson(Person person) {
        personRepository.save(person);
        return Response.status(200).build();
    }

    @PUT
    @Path(value = "/{id}")
    public Response updatePerson(@PathParam("id") Integer personId, Person person) {

        if (person.getId() == null)
            person.setId(personId);

        personRepository.update(person);
        return Response.status(200).build();
    }

    @DELETE
    @Path(value = "/{id}")
    public Response deletePerson(@PathParam("id") Integer personId) {
        personRepository.delete(personId);
        return Response.status(204).build();
    }
}
