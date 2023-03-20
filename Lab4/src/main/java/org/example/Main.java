package org.example;


import java.util.*;



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
            StudentProjectMatcher matcher = new StudentProjectMatcher(NUM_STUDENTS, NUM_PROJECTS, MAX_PREFS);

            // get and print the students with lower preferences
            List<Student> studentsWithLowerPrefs = matcher.getStudentsWithLowerPreferences();
            studentsWithLowerPrefs.forEach(System.out::println);

            // match the students to the projects and print the matches
            Map<Student, Project> matches = matcher.match();
            matches.forEach((student, project) -> System.out.println(student.getName() + " -> " + project.getName()));
        }


    }





