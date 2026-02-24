 package com.app.quantitymeasurement;
 
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

	private static final double EPSILON = 1e-6;

    //  Target unit = FEET
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.FEET
                );

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    // Target unit = INCHES
    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.INCHES
                );

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    //  Target unit = YARDS
    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.YARDS
                );

        assertEquals(
                new QuantityLength(0.6666667, LengthUnit.YARDS),
                result
        );
    }

    //  Target unit = CENTIMETERS
    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.INCHES),
                        new QuantityLength(1.0, LengthUnit.INCHES),
                        LengthUnit.CENTIMETERS
                );

        assertEquals(
                new QuantityLength(5.08, LengthUnit.CENTIMETERS),
                result
        );
    }

    // Commutativity with explicit target
    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength r1 = QuantityLength.add(a, b, LengthUnit.YARDS);
        QuantityLength r2 = QuantityLength.add(b, a, LengthUnit.YARDS);

        assertEquals(r1, r2);
    }

    //  Zero value
    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(5.0, LengthUnit.FEET),
                        new QuantityLength(0.0, LengthUnit.INCHES),
                        LengthUnit.YARDS
                );

        assertEquals(
                new QuantityLength(1.6666667, LengthUnit.YARDS),
                result
        );
    }

    // Negative values
    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(5.0, LengthUnit.FEET),
                        new QuantityLength(-2.0, LengthUnit.FEET),
                        LengthUnit.INCHES
                );

        assertEquals(
                new QuantityLength(36.0, LengthUnit.INCHES),
                result
        );
    }

    //  Null target unit
    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        null
                )
        );
    }
}