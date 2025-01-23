// ConstantsExample.java

public class ConstantsExample {

    // Method that uses the Math class PI constant
    public static double calculateAreaWithMathPI(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    // Method that uses our own less precise version of PI
    public static double calculateAreaWithLessPrecisePI(double radius) {
        // Use a less precise version of PI
        double lessPrecisePI = 3.14;
        return lessPrecisePI * Math.pow(radius, 2);
    }

    public static void main(String[] args) {
        double radius = 5.0;

        // Test with Math class PI constant
        double areaWithMathPI = calculateAreaWithMathPI(radius);
        System.out.println("Area using Math.PI: " + areaWithMathPI);

        // Test with less precise version of PI
        double areaWithLessPrecisePI = calculateAreaWithLessPrecisePI(radius);
        System.out.println("Area using less precise PI: " + areaWithLessPrecisePI);

        // Compare the results
        if (areaWithMathPI == areaWithLessPrecisePI) {
            System.out.println("Both methods produced the same result.");
        } else {
            System.out.println("There is a difference in results.");
        }
    }
}

