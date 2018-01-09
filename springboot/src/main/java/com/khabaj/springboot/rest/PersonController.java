package com.khabaj.springboot.rest;

import com.khabaj.common.domain.Person;
import com.khabaj.common.repository.InMemoryPersonRepository;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    InMemoryPersonRepository personRepository;

    public PersonController() {
        this.personRepository = InMemoryPersonRepository.getInstance();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Resource<Person>> getPersons() {
        return personRepository.getAll().stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Resource<Person>> getPerson(@PathVariable("id") Integer personId) {
        Optional<Person> person = personRepository.getById(personId);

        return person
                .map(this::toResource)
                .map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addPerson(@RequestBody Person person) {
        personRepository.save(person);
        HttpHeaders responseHeaders = new HttpHeaders();
        Link link = linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel();
        responseHeaders.set(HttpHeaders.LOCATION, link.getHref());
        return new ResponseEntity(responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updatePerson(@PathVariable("id") Integer personId, @RequestBody Person person) {

        if (person.getId() == null)
            person.setId(personId);

        personRepository.update(person);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePerson(@PathVariable("id") Integer personId) {
        personRepository.delete(personId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private Resource<Person> toResource(Person person) {
        Resource resource = new Resource(person);
        resource.add(linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel());
        return resource;
    }
}
