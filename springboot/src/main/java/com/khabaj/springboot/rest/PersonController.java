package com.khabaj.springboot.rest;

import com.khabaj.common.domain.Person;
import com.khabaj.common.repository.InMemoryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    InMemoryPersonRepository personRepository;

    @Autowired
    public PersonController(InMemoryPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(personRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") Integer personId) {

        Optional<Person> person = personRepository.getById(personId);
        return person
                .map(p -> new ResponseEntity<>(person.get(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addPerson(@RequestBody Person person) {
        personRepository.save(person);
        return new ResponseEntity(HttpStatus.CREATED);
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
}
