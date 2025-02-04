package csc213.lecture7;

import java.util.Scanner;

public class Main {

    public enum LightState {RED, GREEN, YELLOW, DONE};

    public static void main(String[] args) {
        while(true){
            System.out.println("Bad idea");
        }
    }

    private static void addingASemicolon(){
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        boolean value = false;
        while(value){
            System.out.println("Howdy");
        };

        // Place a semicolon on the end of this while!
        while(value){
            System.out.println("Buddy");
        }
    }

    private static void doWhile(){
        do{
            System.out.println("Will I print!");
        } while(false);
    }

    private static void forExample(){
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter 5 numbers
        System.out.println("Enter 5 numbers:");
        int sum = 0;
        int count = 0;

        // Loop to process each number
        for (int i = 1; i <= 5; i++) {
            System.out.print("Number " + i + ": ");
            int number = scanner.nextInt();

            // Validate input: Skip if the number is negative
            if (number < 0) {
                System.out.println("Negative numbers are not allowed. Skipping...");
                continue; // Skip to the next iteration
            }

            // Add the number to the sum
            sum += number;
            count++;

            // Break the loop if the sum exceeds 100
            if (sum > 100) {
                System.out.println("Sum exceeds 100. Stopping...");
                break; // Exit the loop
            }
        }

        // Output the sum and average
        System.out.println("Total numbers processed: " + count);
        System.out.println("Sum of non-negative numbers: " + sum);

        if (count > 0) {
            double average = (double) sum / count;
            System.out.println("Average of non-negative numbers: " + average);
        } else {
            System.out.println("No non-negative numbers were entered.");
        }

        scanner.close();
    }
    
    private static void loopVersusWhile(){
        int x = 0;
        for (int i = 0; i < 5; ++i) {
        x = x + i;
        }
        int i = 0;
        while (i < 5) {
            x = x + i;
            ++i;
        }
    }

}
