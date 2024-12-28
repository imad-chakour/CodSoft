import java.util.Scanner;
import java.util.Random;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        int score = 0;

        while (true) {
            int randomNumber = rand.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 10;
            boolean isCorrect = false;

            System.out.println("Welcome to the Guessing Game!");
            System.out.println("You have " + maxAttempts + " attempts!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scan.nextInt();

                if (userGuess < randomNumber) {
                    System.out.println("Too low!");
                    attempts++;
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high!");
                    attempts++;
                } else {
                    System.out.println("Congratulations! You are correct.");
                    score += (maxAttempts - attempts);
                    isCorrect = true;
                    break;
                }

                System.out.println("Attempts remaining: " + (maxAttempts - attempts));
            }

            if (!isCorrect) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber + ".");
            }

            System.out.println("Your current score is: " + score);
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scan.next().trim().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        }

        System.out.println("Thanks for playing! Your final score is: " + score);
        scan.close();
    }
}
