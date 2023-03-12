package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Company implements Comparable<Company>, Node {
    private String name;
    private Map<Person, String> employees;

    public Company(String name) {
        this.name = name;
        employees = new HashMap<>();
    }

    //Adauga angajat la companie
    public void addEmployee(Person employee, String position) {
        employees.put(employee, position);
        employee.addRelationship(this, position);
    }

    //Returneaza postul unei persoane
    public String getPosition(Person employee) {
        return employees.get(employee);
    }

    //Returneaza toti angajatii
    public List<Person> getEmployees() {
        return new ArrayList<>(employees.keySet());
    }

    public String getName() {
        return name;
    }

    //Compare 2 companii
    public int compareTo(Company other) {
        return name.compareTo(other.getName());
    }

    @Override
    public String toString() {
        return name + " (Importance: " + getNodeImportance() + ")\n" +
                "  Employees:\n" +
                getEmployeesString();
    }

    //Creaza un string cu toti angajatii pentru afisare.
    private String getEmployeesString() {
        StringBuilder sb = new StringBuilder();
        for (Person employee : employees.keySet()) {
            sb.append("  - ").append(employee.getName()).append(" (")
                    .append(employees.get(employee)).append(")\n");
        }
        return sb.toString();
    }

    public int getNodeImportance() {
        return employees.size();
    }
}
