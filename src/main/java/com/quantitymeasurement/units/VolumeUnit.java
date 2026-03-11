package com.quantitymeasurement.units;

public enum VolumeUnit implements IMeasurable {

    LITER(1),
    MILLILITER(0.001),
    GALLON(3.78541);

    private final double factor;

    VolumeUnit(double factor){
        this.factor = factor;
    }

    public double toBase(double value){
        return value * factor;
    }

    public double fromBase(double baseValue){
        return baseValue / factor;
    }

    public String getMeasurementType(){
        return "VOLUME";
    }
}