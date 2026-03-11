package com.quantitymeasurement.units;

public enum LengthUnit implements IMeasurable {

    FEET(0.3048),
    INCH(0.0254),
    METER(1.0),
    CENTIMETER(0.01);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }

    public String getMeasurementType() {
        return "LENGTH";
    }
}