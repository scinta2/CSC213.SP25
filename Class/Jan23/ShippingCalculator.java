public class ShippingCalculator {

    public double calculateShippingCost(double weight) {
        
        double costPerKg = 2.5;
        public static final double DISCOUNT_THRESHOLD = 10;
        public static final double SURCHARGE = 5;
        
        double shippingCost = weight * costPerKg;

        if (weight > DISCOUNT_THRESHOLD) {
            shippingCost *= 0.9; 
        }
        
        if (weight < 1) {
            shippingCost += SURCHARGE; 
        }

        return shippingCost;
    }

    public static void main(String[] args) {
        Math.
        ShippingCalculator calculator = new ShippingCalculator();

        // Calculate shipping cost for an item weighing 8 kg
        double cost = calculator.calculateShippingCost(8);
        System.out.println("Shipping cost: $" + cost);

        ShippingCalculator.DISCOUNT_THRESHOLD
    }
}
