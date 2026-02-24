package com.app.quantitymeasurement;

import java.util.Objects;

public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 1e-6;

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


    // Instance conversion
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double feetValue = valueInFeet();
        double convertedValue = targetUnit.fromFeet(feetValue);

        return new QuantityLength(convertedValue, targetUnit);
    }

    // Static conversion API
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        return value * (source.getConversionFactor() / target.getConversionFactor());
    }

    // Instance addition : result in unit of first operand
    public QuantityLength add(QuantityLength other) {
        if (other == null) {
            throw new IllegalArgumentException("Second quantity cannot be null");
        }

        double sumInFeet = this.valueInFeet() + other.valueInFeet();
        double resultValue = unit.fromFeet(sumInFeet);

        return new QuantityLength(resultValue, this.unit);
    }

    // Static addition
    public static QuantityLength add(
            QuantityLength a,
            QuantityLength b,
            LengthUnit targetUnit) {

        if (a == null || b == null) {
            throw new IllegalArgumentException("Quantities cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumInFeet = a.valueInFeet() + b.valueInFeet();
        double resultValue = targetUnit.fromFeet(sumInFeet);

        return new QuantityLength(resultValue, targetUnit);
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.valueInFeet() - other.valueInFeet()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(valueInFeet() / EPSILON));
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}