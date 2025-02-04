public class CrazyWhileLoop {
    public static void main(String[] args) {
        int count = 0;
        int maxCount = 10;
        
        // Loop until count reaches maxCount
        while (count < maxCount) {
            // Generate a random number between 0 and 9
            int randomNumber = (int) (Math.random() * 10);
            
            // Print stars based on the random number
            for (int i = 0; i < randomNumber; i++) {
                System.out.print("*");
            }
            
            // Increment count
            count++;
            
            // Pause for a short duration to create a crazy effect
            try {
                Thread.sleep(500); // Sleep for 500 milliseconds (half a second)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Clear the console screen to create animation effect
            System.out.print("\r"); // Move cursor to beginning of line
            for (int i = 0; i < 80; i++) {
                System.out.print(" "); // Overwrite previous output with spaces
            }
            System.out.print("\r"); // Move cursor to beginning of line
            
            // Repeat the loop
        }
    }
}
