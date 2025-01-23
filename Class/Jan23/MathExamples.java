public class MathExamples{


    public static void main(String[] args){
        printFloatingPointExample();
        printDividingByZeroExample();
        printDividingZeroByZeroExample();
        printDividingZeroByNonZeroExample();

    }

    private static void printFloatingPointExample(){
        System.out.println(2.0-1.7);
    }

    private static void printDividingByZeroExample(){
        double result = 10.0 / 0.0;  // Positive infinity
        System.out.println(result);
    }

    private static void printDividingZeroByZeroExample(){
        double result = 0.0 / 0.0;  // Positive infinity
        System.out.println(result);
    }

    private static void printDividingZeroByNonZeroExample(){
        double result = 10 / 0;  // Positive infinity
        System.out.println(result);
    }

}