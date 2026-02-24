package com.app.quantitymeasurement;

public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factorToBase;

    VolumeUnit(double factorToBase) {
        this.factorToBase = factorToBase;
    }

   // getters to access value
    @Override
    public double getConversionFactor() {
        return factorToBase;
    }

    // convert given unit to base unit (litres)
    @Override
    public double convertToBaseUnit(double value) {
        return value * factorToBase;   // → litres
    }

    // convert base unit to required unit
    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factorToBase;
    }

    //getter to access unit
    @Override
    public String getUnitName() {
        return name();
    }
}