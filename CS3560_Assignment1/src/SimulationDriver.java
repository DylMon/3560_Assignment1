// ...

import java.util.*;

public class SimulationDriver {
    private static Map<String, Map<String, Integer>> globalAnswerCounts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        // Create and configure 15 questions
        questions.add(new Question("What is the capital of France?", Arrays.asList("A : Paris", "B : London", "C : Berlin", "D : Rome")));
        questions.add(new Question("Which planet is known as the Red Planet?", Arrays.asList("A : Earth", "B : Mars", "C : Venus", "D : Jupiter")));
        questions.add(new Question("Who wrote the play 'Romeo and Juliet'?", Arrays.asList("A : William Shakespeare", "B : Charles Dickens", "C : Jane Austen", "D : F. Scott Fitzgerald")));
        questions.add(new Question("What is the chemical symbol for gold?", Arrays.asList("A : Go", "B : Au", "C : Ag", "D : Ge")));
        questions.add(new Question("Which country is known as the Land of the Rising Sun?", Arrays.asList("A : China", "B : South Korea", "C : Japan", "D : Thailand")));
        questions.add(new Question("What is the largest mammal in the world?", Arrays.asList("A : Elephant", "B : Giraffe", "C : Blue Whale", "D : Hippopotamus")));
        questions.add(new Question("Which gas is most abundant in the Earth's atmosphere?", Arrays.asList("A : Oxygen", "B : Carbon Dioxide", "C : Nitrogen", "D : Hydrogen")));
        questions.add(new Question("Who painted the Mona Lisa?", Arrays.asList("A : Pablo Picasso", "B : Vincent van Gogh", "C : Leonardo da Vinci", "D : Michelangelo")));
        questions.add(new Question("What is the chemical formula for water?", Arrays.asList("A : H2O", "B : CO2", "C : O2", "D : N2")));
        questions.add(new Question("Which year did Christopher Columbus first arrive in the Americas?", Arrays.asList("A : 1492", "B : 1776", "C : 1812", "D : 1620")));
        questions.add(new Question("Who is the author of 'To Kill a Mockingbird'?", Arrays.asList("A : J.R.R. Tolkien", "B : Jane Austen", "C : Harper Lee", "D : George Orwell")));
        questions.add(new Question("What is the tallest mountain in the world?", Arrays.asList("A : Mount Kilimanjaro", "B : Mount Fuji", "C : Mount Everest", "D : Mount McKinley")));
        questions.add(new Question("Which element is represented by the symbol 'Fe' on the periodic table?", Arrays.asList("A : Iron", "B : Gold", "C : Silver", "D : Copper")));
        questions.add(new Question("What is the largest organ in the human body?", Arrays.asList("A : Liver", "B : Heart", "C : Skin", "D : Brain")));
        questions.add(new Question("Who is known as the 'Father of Modern Physics'?", Arrays.asList("A : Albert Einstein", "B : Isaac Newton", "C : Galileo Galilei", "D : Stephen Hawking")));


        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Start Voting for a Question");
            System.out.println("2. Display Results for a Question");
            System.out.println("3. Exit");

            int option = scanner.nextInt(); // Read the user's selected option
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    System.out.println("Enter the question number (1-15):");
                    int questionNumber = scanner.nextInt(); // Read the user's selected question number
                    scanner.nextLine(); // Consume the newline character

                    if (questionNumber >= 1 && questionNumber <= 15) {
                        Question question = questions.get(questionNumber - 1); // Get the selected question from the list
                        // Create the VotingService with the selected question
                        VotingService votingService = new VotingService(question);

                        // Simulate student submissions
                        Random random = new Random();
                        int numStudents = 100;
                        for (int i = 1; i <= numStudents; i++) {
                            String studentId = "Student" + i;
                            String randomChoice = question.getChoices().get(random.nextInt(question.getChoices().size()));
                            Student student = new Student(studentId, randomChoice);
                            Map<String, Integer> AnswerCounts = votingService.submitAnswer(student);
                            // Submit the student's answer and update globalAnswerCounts
                            globalAnswerCounts.put(question.getQuestion(),AnswerCounts);

                        }

                        System.out.println("Voting completed for Question " + questionNumber);
                    } else {
                        System.out.println("Invalid question number.");
                    }
                    break;

                case 2:
                    System.out.println("Enter the question number (1-15) to display results:");
                    int resultQuestionNumber = scanner.nextInt(); // Read the user's selected question number
                    scanner.nextLine(); // Consume the newline character

                    if (resultQuestionNumber >= 1 && resultQuestionNumber <= 15) {
                        Question resultQuestion = questions.get(resultQuestionNumber - 1);

                        // Check if globalAnswerCounts is empty
                        if (globalAnswerCounts.isEmpty()) {
                            Question question2 = questions.get(resultQuestionNumber - 1); // Get the selected question from the list
                            // Create the VotingService with the selected question
                            VotingService votingService = new VotingService(question2);
                            votingService.displayResults();
                        } else {
                            boolean questionFound = false; // To track if the chosen question is found in globalAnswerCounts

                            // Iterate through globalAnswerCounts
                            for (Map.Entry<String, Map<String, Integer>> entry : globalAnswerCounts.entrySet()) {
                                String question = entry.getKey(); // Get the question from the map

                                // Check if the current question matches the chosen question
                                if (question.equals(resultQuestion.getQuestion())) {
                                    questionFound = true; // Set the flag to true

                                    System.out.println("Results for Question: " + question);

                                    Map<String, Integer> answerCounts = entry.getValue(); // Get the answer counts for the chosen question

                                    for (Map.Entry<String, Integer> answerEntry : answerCounts.entrySet()) {
                                        String choice = answerEntry.getKey(); // Get the answer choice
                                        int count = answerEntry.getValue();   // Get the count for the answer choice

                                        System.out.println(choice + " : " + count); // Print the answer choice and its count
                                    }
                                }
                            }

                            // If the chosen question is not found, display a message
                            if (!questionFound) {
                                System.out.println("No votes for the selected question: " + resultQuestion.getQuestion());
                                Question question2 = questions.get(resultQuestionNumber - 1); // Get the selected question from the list
                                // Create the VotingService with the selected question
                                VotingService votingService = new VotingService(question2);
                                votingService.displayResults();
                            }
                        }
                    } else {
                        System.out.println("Invalid question number.");
                    }

                    break;

                case 3:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    break;
            }
        }

    }
}
