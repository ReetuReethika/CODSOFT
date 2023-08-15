import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attempts = 5;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            int generatedNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int guess;
            int attemptCount = 0;
            boolean correctGuess = false;

            System.out.printf("Guess the number between %d and %d%n", minRange, maxRange);

            while (!correctGuess && attemptCount < attempts) {
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();
                attemptCount++;

                if (guess == generatedNumber) {
                    System.out.println("Congratulations! Your guess is correct!");
                    correctGuess = true;
                } else if (guess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (correctGuess) {
                score += attempts - attemptCount + 1;
            }

            System.out.print("Do you want to play again? (yes/no): ");
        } while (scanner.next().equalsIgnoreCase("yes"));

        System.out.printf("Your final score is: %d%n", score);
        scanner.close();
    }
}
