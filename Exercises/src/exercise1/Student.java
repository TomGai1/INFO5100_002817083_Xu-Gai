package exercise1;

public class Student {
    private String name;
    private int[] quizScores;

    public Student(String name) {
        this.name = name;
        this.quizScores = new int[15];
    }

    public String getName() {
        return name;
    }

    public int[] getQuizScores() {
        return quizScores;
    }

    public void setQuizScores(int[] quizScores) {
        if (quizScores.length == 15) {
            this.quizScores = quizScores;
        } else {
            throw new IllegalArgumentException("Quiz scores must have 15 entries.");
        }
    }

    public double getAverageQuizScore() {
        int sum = 0;
        for (int score : quizScores) {
            sum += score;
        }
        return sum / 15.0;
    }
}
