import java.util.Scanner;
import java.util.Random;
public class GuessingGameTask1 {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalAttempts = 0;
        int roundsWon = 0;
        System.out.println("\nWelcome to the Number Guessing Game!\n");
        while (playAgain) {
            int generatedNumber = random.nextInt(100) + 1;
            int attempts = 0;
            System.out.println("Instructions:");
            System.out.println("- You have to guess the number generated between 1 to 100.");
            System.out.println("- You have 5 attempts to guess the correct number.");
            System.out.println("- After each guess, you will be informed if your guess is too high or too low.\n");
            System.out.println("Round " + (roundsWon + 1) + ":");
            // Loop for up to 5 attempts given to the user
            boolean guessedCorrectly = false;
            while (attempts < 5) {
                System.out.print("Guess the number: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    sc.next(); // Clear the invalid input
                    continue;
                }
                int guess = sc.nextInt();
                attempts++;
                if (guess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    totalAttempts += attempts;
                    roundsWon++;
                    guessedCorrectly = true;
                    break;
                } else if (guess < generatedNumber) {
                    System.out.println("Too low, the number is greater than " + guess + ".\nYou have " + (5 - attempts) + " attempts left.\n");
                } else {
                    System.out.println("Too high, the number is less than " + guess + ".\nYou have " + (5 - attempts) + " attempts left.\n");
                }
            }
            if (!guessedCorrectly) {
                // Checking if the last guess was incorrect
                System.out.println("Sorry, you didn't guess the correct number.\nThe correct number was: " + generatedNumber);
            }
            System.out.println("\nScore: " + roundsWon + " round(s) won.\n");
            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = sc.next();
            if (!playChoice.equalsIgnoreCase("yes")) {
                playAgain = false;
            } else {
                System.out.println(); // Adding a blank line for better readability
            }
        }
        System.out.println("\nThank you for playing!");
        System.out.println("Total Attempts: " + totalAttempts);
        System.out.println("Total Rounds Won: " + roundsWon);
        sc.close();
    }
}