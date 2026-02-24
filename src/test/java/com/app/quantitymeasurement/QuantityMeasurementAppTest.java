package com.app.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    //  Same-unit conversion
    @Test
    void testConvert_FeetToFeet() {
        assertEquals(5.0,
                QuantityLength.convert(5.0, LengthUnit.FEET, LengthUnit.FEET),
                EPSILON);
    }

    //  Feet to Inches
    @Test
    void testConvert_FeetToInches() {
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON);
    }

    // Inches to Feet
    @Test
    void testConvert_InchesToFeet() {
        assertEquals(2.0,
                QuantityLength.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET),
                EPSILON);
    }

    //  Yards to Feet
    @Test
    void testConvert_YardsToFeet() {
        assertEquals(6.0,
                QuantityLength.convert(2.0, LengthUnit.YARDS, LengthUnit.FEET),
                EPSILON);
    }

    // Feet to Yards
    @Test
    void testConvert_FeetToYards() {
        assertEquals(2.0,
                QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS),
                EPSILON);
    }

    //  Yards to Inches
    @Test
    void testConvert_YardsToInches() {
        assertEquals(36.0,
                QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES),
                EPSILON);
    }

    //  Inches to Yards
    @Test
    void testConvert_InchesToYards() {
        assertEquals(2.0,
                QuantityLength.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS),
                EPSILON);
    }

    //  Centimeters to Inches
    @Test
    void testConvert_CentimetersToInches() {
        assertEquals(1.0,
                QuantityLength.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES),
                EPSILON);
    }

    //  Zero value conversion
    @Test
    void testConvert_ZeroValue() {
        assertEquals(0.0,
                QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON);
    }

    //Negative value conversion
    @Test
    void testConvert_NegativeValue() {
        assertEquals(-12.0,
                QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON);
    }

    // Round-trip conversion
    @Test
    void testConvert_RoundTrip() {
        double value = 5.5;
        double inches = QuantityLength.convert(value, LengthUnit.FEET, LengthUnit.INCHES);
        double feet = QuantityLength.convert(inches, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(value, feet, EPSILON);
    }

    //  Instance method conversion
    @Test
    void testInstanceConvertTo() {
        QuantityLength length = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength converted = length.convertTo(LengthUnit.FEET);

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), converted);
    }

    //  Null source unit
    @Test
    void testConvert_NullSourceUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0, null, LengthUnit.FEET));
    }

    //  Null target unit
    @Test
    void testConvert_NullTargetUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0, LengthUnit.FEET, null));
    }

    // NaN value
    @Test
    void testConvert_NaNValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
    }

    // Infinite value
    @Test
    void testConvert_InfiniteValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES));
    }
}