public class ShippingCalculator {

    // 1. Define constants as class-level fields (static final)
    public static final double COST_PER_KG = 2.5;
    public static final double DISCOUNT_THRESHOLD = 10.0;
    public static final double DISCOUNT_RATE = 0.9;
    public static final double SURCHARGE = 5.0;

    public double calculateShippingCost(double weight) {
        // 2. Use the COST_PER_KG constant instead of a local hard-coded value
        double shippingCost = weight * COST_PER_KG;
        
        // 3. Apply discount if weight exceeds DISCOUNT_THRESHOLD
        if (weight > DISCOUNT_THRESHOLD) {
            shippingCost *= DISCOUNT_RATE; 
        }

        // 4. Apply a surcharge if weight is less than 1kg
        if (weight < 1) {
            shippingCost += SURCHARGE; 
        }

        return shippingCost;
    }

    public static void main(String[] args) {

        // 5. Create an instance of ShippingCalculator
        ShippingCalculator calculator = new ShippingCalculator();

        // 6. Calculate shipping cost for an 8 kg item
        double cost = calculator.calculateShippingCost(8.0);
        System.out.println("Shipping cost: $" + cost);

        // 7. Access class-level constants directly if needed
        System.out.println("Discount threshold is: " + DISCOUNT_THRESHOLD + " kg");
    }
}

