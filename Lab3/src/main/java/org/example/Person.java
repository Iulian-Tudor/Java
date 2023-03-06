package org.example;

import java.util.ArrayList;
import java.util.List;

class Person implements Node, Comparable<Person> {
    private String name;
    private List<Person> friends;
    private Company company;

    public Person(String name) {
        this.name = name;
        this.friends = new ArrayList<>();
        this.company = null;
    }

    public void addFriend(Person person) {
        this.friends.add(person);
    }

    public List<Person> getFriends() {
        return this.friends;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", friends=" + friends +
                ", company=" + company +
                '}';
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return this.company;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}