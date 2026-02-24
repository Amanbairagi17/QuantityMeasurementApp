package com.app.quantitymeasurement;

public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double getConversionFactor() {
        return toFeetFactor;
    }

    // Convert value in this unit to base unit (feet)
    public double convertToBaseUnit(double value) {
        validate(value);
        return value * toFeetFactor;
    }

    // Convert value from base unit (feet) to this unit
    public double convertFromBaseUnit(double baseValue) {
        validate(baseValue);
        return baseValue / toFeetFactor;
    }

    private void validate(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
    }
}