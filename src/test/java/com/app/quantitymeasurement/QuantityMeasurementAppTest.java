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
        
        @Test
        void testEquality_YardToFeet() {
            assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET)
            );
        }

        @Test
        void testEquality_YardToInches() {
            assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(36.0, LengthUnit.INCH)
            );
        }

        @Test
        void testEquality_CentimeterToInch() {
            assertEquals(
                new QuantityLength(1.0, LengthUnit.CENTIMETERS),
                new QuantityLength(0.393701, LengthUnit.INCH)
            );
        }

        @Test
        void testEquality_MultiUnit_Transitive() {
            QuantityLength a = new QuantityLength(1.0, LengthUnit.YARDS);
            QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);
            QuantityLength c = new QuantityLength(36.0, LengthUnit.INCH);

            assertEquals(a, b);
            assertEquals(b, c);
            assertEquals(a, c);
        }

        @Test
        void testEquality_NonEquivalent() {
            assertNotEquals(
                new QuantityLength(1.0, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.FEET)
            );
    }
}