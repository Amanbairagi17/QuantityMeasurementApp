package com.app.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    // Constructor
    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    // Getters
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    //Conversion 

    public double convertTo(U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        validateSameCategory(targetUnit);

        double baseValue = unit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    // Addition 

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateOther(other);
        validateSameCategory(targetUnit);

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double totalBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(totalBase);
        return new Quantity<>(round(result), targetUnit);
    }

    // Subtraction (UC12) 

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateOther(other);
        validateSameCategory(targetUnit);

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double diffBase = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(diffBase);
        return new Quantity<>(round(result), targetUnit);
    }

    // Division (UC12) 

    public double divide(Quantity<U> other) {
        validateOther(other);

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (Math.abs(base2) < EPSILON)
            throw new ArithmeticException("Cannot divide by zero");

        return base1 / base2;
    }

    // Validation Helpers 
    private void validateOther(Quantity<U> other) {
        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");
        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");
    }

    private void validateSameCategory(U targetUnit) {
        if (!unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");
    }

    // equals & hashCode 

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> that)) return false;
        if (!unit.getClass().equals(that.unit.getClass())) return false;

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = that.unit.convertToBaseUnit(that.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(unit.getClass(), Math.round(base / EPSILON));
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // Demo main
//    public static void main(String[] args) {
//
//        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
//        Quantity<LengthUnit> inches = new Quantity<>(120.0, LengthUnit.INCHES);
//
//        System.out.println("Length equal: " + feet.equals(inches));
//        System.out.println("10 feet in inches: " + feet.convertTo(LengthUnit.INCHES));
//        System.out.println("Add: " + feet.add(inches, LengthUnit.FEET));
//        System.out.println("Subtract: " + feet.subtract(new Quantity<>(6.0, LengthUnit.INCHES)));
//
//        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
//        Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
//
//        System.out.println("Weight equal: " + kg.equals(gram));
//        System.out.println("Divide: " + kg.divide(gram));
//
//        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
//        System.out.println("Volume equal: " + litre.equals(ml));
//        System.out.println("Add volume: " + litre.add(ml));
//    }
}