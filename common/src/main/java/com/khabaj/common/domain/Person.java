package com.khabaj.common.domain;

import java.util.Set;

public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private Set<String> phones;

    public Person() {
    }

    public Person(Integer id, String firstName, String lastName) {
        this(id, firstName,lastName, null);
    }

    public Person(Integer id, String firstName, String lastName, Set<String> phones) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }
}
