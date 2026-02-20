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
    
    //Inch Equality
    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    //for equality
    public static boolean checkInchesEquality(double i1, double i2) {
        Inches inch1 = new Inches(i1);
        Inches inch2 = new Inches(i2);
        return inch1.equals(inch2);
    }
    
    public static boolean checkFeetEquality(double f1, double f2) {
        Feet feet1 = new Feet(f1);
        Feet feet2 = new Feet(f2);
        return feet1.equals(feet2);
    }
    
    
    public static void main(String[] args) {
    	// UC1 – Feet equality
        System.out.println("Feet Equal: " + checkFeetEquality(1.0, 1.0));

        // UC2 – Inches equality
        System.out.println("Inches Equal: " + checkInchesEquality(1.0, 1.0));
    }
}
