package org.example;

import java.util.ArrayList;
import java.util.List;

class Company implements Node, Comparable<Company> {
    private String name;
    private List<Person> employees;

    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }

    public void addEmployee(Person employee) {
        this.employees.add(employee);
        employee.setCompany(this);
    }

    public List<Person> getEmployees() {
        return this.employees;
    }

    @Override
    public int compareTo(Company other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}