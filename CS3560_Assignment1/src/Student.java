class Student {
    private final String id;     // Private field to store student ID
    private final String answer; // Private field to store student's answer to a question

    // Constructor to initialize student ID and answer
    public Student(String id, String answer) {
        this.id = id;       // Initialize the id field with the provided ID
        this.answer = answer; // Initialize the answer field with the provided answer
    }

    // Getter method to retrieve the student ID
    public String getId() {
        return id; // Return the student ID
    }

    // Getter method to retrieve the student's answer
    public String getAnswer() {
        return answer; // Return the student's answer
    }
}
