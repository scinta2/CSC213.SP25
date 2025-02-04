package csc213.lecture7;

public class CrazyForLoop {
    public static void main(String[] args) {
        int size = 10;
        
        // Upper half of the diamond
        for (int i = 1; i <= size; i++) {
            // Print spaces
            for (int j = size - i; j > 0; j--) {
                System.out.print(" ");
            }
            
            // Print stars
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            
            System.out.println();
        }
        
        // Lower half of the diamond
        for (int i = size - 1; i >= 1; i--) {
            // Print spaces
            for (int j = 1; j <= size - i; j++) {
                System.out.print(" ");
            }
            
            // Print stars
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            
            System.out.println();
        }
    }
}

