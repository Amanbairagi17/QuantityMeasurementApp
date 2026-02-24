package com.app.quantitymeasurement;

public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    //getters
    @Override
    public double getConversionFactor() {
        return factor;
    }

    //method to convert to base unit(feet)
    @Override
    public double convertToBaseUnit(double value) {
        validate(value);
        return value * factor;
    }

    //method that convert form base unit to required unit
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

    //method to handle unwanted input
    private void validate(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
    }
}