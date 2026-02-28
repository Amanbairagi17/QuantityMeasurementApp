package com.app.quantitymeasurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    // Constructor
    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    // Getters
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    //   UC13 – ArithmeticOperation ENUM

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if (Math.abs(b) < EPSILON)
                throw new ArithmeticException("Cannot divide by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    //   UC13 – Centralized Validation

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean requireTarget
    ) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (requireTarget) {
            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            if (!unit.getClass().equals(targetUnit.getClass()))
                throw new IllegalArgumentException("Cross-category operation not allowed");
        }
    }

     //  UC13 – Core Base Arithmetic

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

      // Conversion

    public double convertTo(U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");

        double baseValue = unit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

     //  ADD (Refactored)

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(result), targetUnit);
    }

      // SUBTRACT (Refactored)

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(result), targetUnit);
    }

    //   DIVIDE (Refactored)

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

      // equals & hashCode (UNCHANGED)

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> that)) return false;
        if (!unit.getClass().equals(that.unit.getClass())) return false;

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = that.unit.convertToBaseUnit(that.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(unit.getClass(), Math.round(base / EPSILON));
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}