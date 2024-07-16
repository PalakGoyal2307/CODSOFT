import java.util.*;
import java.util.concurrent.*;

public class QuizApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static int questionIndex = 0;
    private static final int TIME_LIMIT = 10; 

    public static void main(String[] args) {
        initializeQuestions();
        startQuiz();
        displayResults();
    }

    private static void initializeQuestions() {
        questions.add(new Question("Which is the largest island?", 
            new String[]{"A. New Guinea", "B. Andaman Nicobar", "C. Greenland", "D. Hawaii"}, 'C'));
        questions.add(new Question("Which one is the hottest continent?", 
            new String[]{"A. Africa", "B.South Asia", "C. North America", "D. Australia"}, 'A'));
        questions.add(new Question("How many bones are in the body of an adult human?", 
            new String[]{"A. 330", "B. 206", "C. 250", "D. 210"}, 'B'));
    }

    private static void startQuiz() {
        for (Question question : questions) {
            displayQuestion(question);
        }
    }

    private static void displayQuestion(Question question) {
        System.out.println(question.getQuestionText());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Future<String> future = executorService.submit(() -> scanner.nextLine());
        try {
            String answer = future.get(TIME_LIMIT, TimeUnit.SECONDS);
            if (question.isCorrect(answer.charAt(0))) {
                score++;
            }
        } catch (TimeoutException e) {
            System.out.println("Time's up!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdownNow();
        }
        questionIndex++;
    }

    private static void displayResults() {
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
        System.out.println("Summary of answers:");
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            System.out.println("Correct answer: " + question.getCorrectAnswer());
            if (questionIndex <= questions.size() && question.isCorrect(question.getAnswer())) {
                System.out.println("Your answer was correct.");
            } else {
                System.out.println("Your answer was incorrect.");
            }
        }
    }
}
public class Question {
    private String questionText;
    private String[] options;
    private char correctAnswer;
    private char answer;

    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }

    public char getAnswer() {
        return answer;
    }

    public boolean isCorrect(char answer) {
        this.answer = answer;
        return this.correctAnswer == answer;
    }
}
