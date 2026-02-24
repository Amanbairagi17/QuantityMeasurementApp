package com.app.quantitymeasurement;

public interface IMeasurable {

    double getConversionFactor();              // relative to base unit
    double convertToBaseUnit(double value);    // → base unit
    double convertFromBaseUnit(double base);   // ← base unit
    String getUnitName();                      // readable name
}