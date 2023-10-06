import java.util.List;

class Question {
    private final String question;  // Private field to store the question text
    private final List<String> choices; // Private field to store the list of choices for the question

    // Constructor to initialize the question and choices
    public Question(String question, List<String> choices) {
        this.question = question;   // Initialize the question field with the provided question text
        this.choices = choices;     // Initialize the choices field with the provided list of choices
    }

    // Getter method to retrieve the question text
    public String getQuestion() {
        return question; // Return the question text
    }

    // Getter method to retrieve the list of choices
    public List<String> getChoices() {
        return choices; // Return the list of choices
    }
}
