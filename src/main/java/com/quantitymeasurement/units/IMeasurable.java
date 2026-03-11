package com.quantitymeasurement.units;

public interface IMeasurable {

    double toBase(double value);

    double fromBase(double baseValue);

    String getMeasurementType();
}