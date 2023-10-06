import java.util.HashMap;
import java.util.Map;

class VotingService {
    private final Question question;            // Private field to store the question being voted on
    private final Map<String, Integer> answerCounts; // Private field to store the counts of each answer choice

    // Constructor with only a Question object
    public VotingService(Question question) {
        this.question = question;                      // Initialize the question field with the provided question
        this.answerCounts = new HashMap<>();           // Initialize the answerCounts map
        for (String choice : question.getChoices()) {
            answerCounts.put(choice, 0);               // Initialize counts to 0 for each choice
        }
    }

    // Method to submit a student's answer and return the updated answerCounts map
    public Map<String, Integer> submitAnswer(Student student) {
        String answer = student.getAnswer();            // Get the student's answer
        if (question.getChoices().contains(answer)) {
            answerCounts.put(answer, answerCounts.get(answer) + 1); // Increment the count for the chosen answer
        } else {
            System.out.println("Invalid answer: " + answer); // Print an error message for an invalid answer
        }

        return answerCounts; // Return the updated answerCounts map
    }

    // Method to display the voting results
    public void displayResults() {
        System.out.println("Results for Question: " + question.getQuestion()); // Print the question text
        for (Map.Entry<String, Integer> entry : answerCounts.entrySet()) {
            String choice = entry.getKey();     // Get the answer choice
            int count = entry.getValue();       // Get the count for the answer choice
            System.out.println(choice + " : " + count); // Print the answer choice and its count
        }
    }
}
