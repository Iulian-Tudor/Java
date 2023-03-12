package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // studenti
        Student s0 = new Student("S0", Stream.of("P0", "P1", "P2").map(Project::new).collect(Collectors.toSet()));
        Student s1 = new Student("S1", Stream.of("P0", "P1").map(Project::new).collect(Collectors.toSet()));
        Student s2 = new Student("S2", Stream.of("P0").map(Project::new).collect(Collectors.toSet()));

        // Lista inlantuita
        List<Student> students = Stream.of(s0, s1, s2).sorted().collect(Collectors.toCollection(LinkedList::new));
        students.forEach(System.out::println);

        // proiecte
        Project p0 = new Project("P0");
        Project p1 = new Project("P1");
        Project p2 = new Project("P2");

        // TreeSet
        Set<Project> projects = Stream.of(p0, p1, p2).collect(Collectors.toCollection(TreeSet::new));
        projects.forEach(System.out::println);
    }
}




