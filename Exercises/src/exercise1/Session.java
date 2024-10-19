package exercise1;

import java.util.ArrayList;
import java.util.Collections;

public class Session {
    private ArrayList<Student> students;

    public Session() {
        students = new ArrayList<Student>();
    }

    public void addStudent(Student s) {
        if (students.size() < 20) {
            students.add(s);
        } else {
            throw new IllegalStateException("Cannot add more than 20 students to the session.");
        }
    }

    // Calculate average quiz scores per student for the whole class
    public void calculateAverageQuizScores() {
        System.out.println("Average Quiz Scores per Student:");
        for (Student s : students) {
            double avg = s.getAverageQuizScore();
            System.out.println("Student: " + s.getName() + ", Average Quiz Score: " + avg);
        }
    }

    // Print the list of all quiz scores in ascending order for the session
    public void printQuizScoresAscending() {
        ArrayList<Integer> allQuizScores = new ArrayList<>();
        for (Student s : students) {
            for (int score : s.getQuizScores()) {
                allQuizScores.add(score);
            }
        }
        Collections.sort(allQuizScores);
        System.out.println("All Quiz Scores in Ascending Order:");
        for (int score : allQuizScores) {
            System.out.print(score + " ");
        }
        System.out.println();
    }

    // Print names of part-time students
    public void printPartTimeStudentNames() {
        System.out.println("Part-Time Students:");
        for (Student s : students) {
            if (s instanceof PartTime) {
                System.out.println(s.getName());
            }
        }
    }

    // Print exam scores of full-time students
    public void printFullTimeExamScores() {
        System.out.println("Full-Time Students' Exam Scores:");
        for (Student s : students) {
            if (s instanceof FullTime) {
                FullTime ft = (FullTime) s;
                System.out.println("Student: " + ft.getName() + ", Exam Score 1: " + ft.getExamScore1()
                        + ", Exam Score 2: " + ft.getExamScore2());
            }
        }
    }
}
