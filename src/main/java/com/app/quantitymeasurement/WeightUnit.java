package com.app.quantitymeasurement;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    // getter to access value
    @Override
    public double getConversionFactor() {
        return factor;
    }

    //method for converting given unit to base unit
    @Override
    public double convertToBaseUnit(double value) {
        validate(value);
        return value * factor;
    }

    //method for converting base unit to required unit
    @Override
    public double convertFromBaseUnit(double base) {
        validate(base);
        return base / factor;
    }

    //getter to access unit
    @Override
    public String getUnitName() {
        return name();
    }

    //validating input 
    private void validate(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
    }
}