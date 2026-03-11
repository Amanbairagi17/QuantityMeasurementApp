package com.quantitymeasurement.entity;

import com.quantitymeasurement.units.IMeasurable;

public class QuantityModel {

    private double value;
    private IMeasurable unit;

    public QuantityModel(double value,IMeasurable unit){
        this.value = value;
        this.unit = unit;
    }

    public double getBaseValue(){
        return unit.toBase(value);
    }

    public IMeasurable getUnit(){
        return unit;
    }
}