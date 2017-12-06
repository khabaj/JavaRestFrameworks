package com.khabaj.common.repository;

import com.khabaj.common.domain.Person;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class InMemoryPersonRepository {

    private final AtomicInteger idGenerator = new AtomicInteger(1);
    private final Map<Integer, Person> repository = new ConcurrentHashMap<>();

    public InMemoryPersonRepository() {
        initializeRepository();
    }

    private void initializeRepository() {
        repository.putAll(
                Stream.generate(() -> new Person(idGenerator.get(),
                        "FirstName" + idGenerator.get(),
                        "LastName" + idGenerator.getAndIncrement(),
                        Stream.generate(() -> {
                            PhoneGenerator phoneGenerator = new PhoneGenerator();
                            return phoneGenerator.generatePhoneNumber();
                        }).limit(3).collect(Collectors.toSet())))
                        .limit(30)
                        .collect(Collectors.toConcurrentMap(Person::getId, Function.identity())
                        ));
    }

    public Optional<Person> getById(Integer id) {
        return Optional.ofNullable(repository.get(id));
    }

    public List<Person> getAll() {
        return new ArrayList<>(repository.values());
    }

    public void save(Person person) {
        if (person.getId() == null) {
            person.setId(idGenerator.getAndIncrement());
        }
        repository.put(person.getId(), person);
    }

    public void update(Person person) {
        save(person);
    }

    public void delete(Integer id) {
        repository.remove(id);
    }

    class PhoneGenerator {
        final Random generator = new Random();

        String generatePhoneNumber() {
            int part1 = generator.nextInt(600) + 100;
            int part2 = generator.nextInt(899999) + 100000;
            return Integer.toString(part1) + Integer.toString(part2);
        }
    }
}
