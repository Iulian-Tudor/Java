package org.example;

import java.util.Set;

public class Student implements Comparable<Student> {
    private final String name;
    private final Set<Project> admissibleProjects;

    public Student(String name, Set<Project> admissibleProjects) {
        this.name = name;
        this.admissibleProjects = admissibleProjects;
    }

    public String getName() {
        return name;
    }

    public Set<Project> getAdmissibleProjects() {
        return admissibleProjects;
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", admissibleProjects=" + admissibleProjects +
                '}';
    }
}

