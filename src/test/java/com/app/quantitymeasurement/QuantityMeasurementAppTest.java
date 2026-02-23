package com.app.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	@Test
    void testEquality_FeetToFeet_SameValue() {
        assertEquals(
            new QuantityLength(1.0, LengthUnit.FEET),
            new QuantityLength(1.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        assertEquals(
            new QuantityLength(1.0, LengthUnit.INCH),
            new QuantityLength(1.0, LengthUnit.INCH)
        );
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        assertEquals(
            new QuantityLength(12.0, LengthUnit.INCH),
            new QuantityLength(1.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        assertNotEquals(
            new QuantityLength(1.0, LengthUnit.FEET),
            new QuantityLength(2.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(q, null);
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(q, q);
    }

    @Test
    void testInvalidUnit() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new QuantityLength(1.0, null)
        );
    }
}