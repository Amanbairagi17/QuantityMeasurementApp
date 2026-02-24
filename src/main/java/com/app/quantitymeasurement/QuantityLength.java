package com.app.quantitymeasurement;

import java.util.Objects;

public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    private double valueInFeet() {
        return unit.toFeet(value);
    }

    // instance conversion
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double feetValue = valueInFeet();
        double convertedValue = targetUnit.fromFeet(feetValue);

        return new QuantityLength(convertedValue, targetUnit);
    }

    //static API
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        return value * (source.getConversionFactor() / target.getConversionFactor());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;
        return Double.compare(this.valueInFeet(), other.valueInFeet()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueInFeet());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}