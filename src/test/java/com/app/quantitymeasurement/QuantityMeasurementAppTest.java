package com.app.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {
	
	private static final double EPSILON = 0.0001;
	
	@Test
    void testFeetConversionFactor() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    void testInchesConversionFactor() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON);
    }

    @Test
    void testYardsConversionFactor() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
    }

    @Test
    void testCentimetersConversionFactor() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
    }

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
            new QuantityLength(1.0, LengthUnit.INCHES),
            new QuantityLength(1.0, LengthUnit.INCHES)
        );
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        assertEquals(
            new QuantityLength(12.0, LengthUnit.INCHES),
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
    void testConvertFeetToInches() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testConvertInchesToFeet() {
        QuantityLength result =
                new QuantityLength(12.0, LengthUnit.INCHES).convertTo(LengthUnit.FEET);
        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), result);
    }

    @Test
    void testConvertFeetToYards() {
        QuantityLength result =
                new QuantityLength(3.0, LengthUnit.FEET).convertTo(LengthUnit.YARDS);
        assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), result);
    }

    @Test
    void testConvertCentimetersToInches() {
        QuantityLength result =
                new QuantityLength(2.54, LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES);
        assertEquals(new QuantityLength(1.0, LengthUnit.INCHES), result);
    }

    @Test
    void testConvertZeroValue() {
        QuantityLength result =
                new QuantityLength(0.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(new QuantityLength(0.0, LengthUnit.INCHES), result);
    }

   

    @Test
    void testAddFeetAndInchesResultFeet() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddFeetAndFeet() {
        QuantityLength result = new QuantityLength(2.0, LengthUnit.FEET)
                        .add(new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.FEET);

        assertEquals(new QuantityLength(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddResultInYards() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(2.0, LengthUnit.FEET), LengthUnit.YARDS);

        assertEquals(new QuantityLength(1.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddCentimetersAndFeet() {
        QuantityLength result =
                new QuantityLength(30.48, LengthUnit.CENTIMETERS)
                        .add(new QuantityLength(1.0, LengthUnit.FEET), LengthUnit.FEET);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddWithZero() {
        QuantityLength result =
                new QuantityLength(5.0, LengthUnit.FEET)
                        .add(new QuantityLength(0.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(new QuantityLength(5.0, LengthUnit.FEET), result);
    }


    @Test
    void testNullUnitThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testNaNValueThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testInfiniteValueThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    @Test
    void testHashCodeConsistencyForEqualObjects() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    void testRoundTripConversion() {
        QuantityLength original = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength roundTrip =
                original.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.FEET);

        assertEquals(original, roundTrip);
    }
    
}