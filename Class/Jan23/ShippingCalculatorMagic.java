public class ShippingCalculatorMagic {

    public double calculateShippingCost(double weight) {
        // "magic number" 2.5 here
        double shippingCost = weight * 2.5;

        // "magic number" 10 here
        if (weight > 10) {
            // Another "magic number" 0.9 for a discount
            shippingCost *= 0.9;
        }

        // "magic number" 1 here
        if (weight < 1) {
            // "magic number" 5 here for a surcharge
            shippingCost += 5;
        }

        return shippingCost;
    }

    public static void main(String[] args) {
        ShippingCalculatorMagic calculator = new ShippingCalculatorMagic();

        // "magic number" 8 here for item weight
        double cost = calculator.calculateShippingCost(8);
        System.out.println("Shipping cost: $" + cost);
    }
}

