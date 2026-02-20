package com;

public class QuantityMeasurementApp {
	// Inner class representing Feet measurement
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            // Step 1: Same reference check
            if (this == obj) return true;

            // Step 2: Null check
            if (obj == null) return false;

            // Step 3: Type check
            if (this.getClass() != obj.getClass()) return false;

            // Step 4: Safe casting
            Feet other = (Feet) obj;

            // Step 5: Value comparison
            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static void main(String[] args) {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Equal: " + f1.equals(f2));
    }
}
