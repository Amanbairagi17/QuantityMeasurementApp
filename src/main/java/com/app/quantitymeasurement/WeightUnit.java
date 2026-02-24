package com.app.quantitymeasurement;
public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKilogramFactor;

    WeightUnit(double toKilogramFactor) {
        this.toKilogramFactor = toKilogramFactor;
    }

    public double getConversionFactor() {
        return toKilogramFactor;
    }

    // Convert value to use unit (kilogram)
    public double convertToBaseUnit(double value) {
        validate(value);
        return value * toKilogramFactor;
    }

    // Convert value from base unit (kilogram)
    public double convertFromBaseUnit(double baseValue) {
        validate(baseValue);
        return baseValue / toKilogramFactor;
    }

    private void validate(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
    }
}