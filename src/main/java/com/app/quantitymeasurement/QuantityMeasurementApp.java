package com.app.quantitymeasurement;

public class QuantityMeasurementApp {
    
    public static void main(String[] args) {
    	// UC1 – LengthUnit
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);
        
        System.out.println(q1.equals(q2));
        
    }
}
