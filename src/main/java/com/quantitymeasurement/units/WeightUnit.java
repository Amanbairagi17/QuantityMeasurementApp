package com.quantitymeasurement.units;

public enum WeightUnit implements IMeasurable {

    GRAM(1),
    KILOGRAM(1000),
    POUND(453.592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }

    public String getMeasurementType() {
        return "WEIGHT";
    }
}