package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// Compulsory Main
/*
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

        // Treeset
        Set<Project> projects = Stream.of(p0, p1, p2).collect(Collectors.toCollection(TreeSet::new));
        projects.forEach(System.out::println);
    }
} */

    public class Main {
        private static final int NUM_STUDENTS = 10;
        private static final int NUM_PROJECTS = 5;
        private static final int MAX_PREFS = 3;

        public static void main(String[] args) {
            List<Student> students = generateStudents(NUM_STUDENTS, NUM_PROJECTS, MAX_PREFS);
            students.forEach(System.out::println);

            List<Student> studentsWithFewerPrefs = getStudentsWithFewerPreferences(students);
            studentsWithFewerPrefs.forEach(System.out::println);
        }

        private static List<Student> generateStudents(int numStudents, int numProjects, int maxPrefs) {
            Faker faker = new Faker();
            List<Project> projects = Stream.generate(() -> new Project(faker.book().title())).limit(numProjects).collect(Collectors.toList());

            //genereaza random studenti si preferinte
            List<Student> students = new ArrayList<>();
            for (int i = 0; i < numStudents; i++) {
                Set<Project> prefs = new HashSet<>();
                int numPrefs = faker.random().nextInt(maxPrefs) + 1;
                for (int j = 0; j < numPrefs; j++) {
                    prefs.add(projects.get(faker.random().nextInt(numProjects)));
                }
                students.add(new Student(faker.name().fullName(), prefs));
            }

            return students;
        }

        //selecteaza studentii sub medie
        private static List<Student> getStudentsWithFewerPreferences(List<Student> students) {
            double avgPrefs = students.stream()
                    .mapToDouble(student -> student.getAdmissibleProjects().size()) //creaza map de tip student - nr.pref
                    .average()
                    .orElse(0.0); //in caz in care nu sunt stundeti

            return students.stream()
                    .filter(student -> student.getAdmissibleProjects().size() < avgPrefs)
                    .collect(Collectors.toList());
        }
    }





