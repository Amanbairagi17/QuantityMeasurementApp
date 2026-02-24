package com.app.quantitymeasurement;

public class QuantityMeasurementApp {

//    public static void demonstrateLengthConversion(
//            double value, LengthUnit from, LengthUnit to) {
//
//        double result = QuantityLength.convert(value, from, to);
//        System.out.println("convert(" + value + ", " + from + ", " + to + ") → " + result);
//    }
//
//    public static void demonstrateLengthConversion(
//            QuantityLength length, LengthUnit to) {
//
//        QuantityLength converted = length.convertTo(to);
//        System.out.println(length + " → " + converted);
//    }
//
//    public static void demonstrateLengthEquality(
//            QuantityLength a, QuantityLength b) {
//
//        System.out.println(a + " == " + b + " → " + a.equals(b));
//    }

    public static void main(String[] args) {

//        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
//        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
//        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
//        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);

    	QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
    	QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

    	System.out.println(a.add(b)); // 2.0 FEET
    	System.out.println(b.add(a)); // 24.0 INCHES
    }
}