package exercise1;

public class Main {
    public static void main(String[] args) {
        Session session = new Session();

        // Populate session with 20 students and dummy scores
        for (int i = 0; i < 20; i++) {
            Student s;
            if (i % 2 == 0) {
                // Even index: Full-Time student
                s = new FullTime("FullTimeStudent" + i);
                ((FullTime) s).setExamScore1((int) (Math.random() * 100));
                ((FullTime) s).setExamScore2((int) (Math.random() * 100));
            } else {
                // Odd index: Part-Time student
                s = new PartTime("PartTimeStudent" + i);
            }

            // Set dummy quiz scores
            int[] quizScores = new int[15];
            for (int j = 0; j < 15; j++) {
                quizScores[j] = (int) (Math.random() * 100);
            }
            s.setQuizScores(quizScores);

            session.addStudent(s);
        }

        // Call all public methods of Session and capture the output on console
        session.calculateAverageQuizScores();
        session.printQuizScoresAscending();
        session.printPartTimeStudentNames();
        session.printFullTimeExamScores();
    }
}
