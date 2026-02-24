 package com.app.quantitymeasurement;
 
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // Same unit: feet + feet
    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(2.0, LengthUnit.FEET));

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    //  Same unit: inches + inches
    @Test
    void testAddition_SameUnit_InchPlusInch() {
        QuantityLength result =
                new QuantityLength(6.0, LengthUnit.INCHES)
                        .add(new QuantityLength(6.0, LengthUnit.INCHES));

        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), result);
    }

    // Cross-unit: feet + inches
    @Test
    void testAddition_FeetPlusInches() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES));

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    // Cross-unit: inches + feet
    @Test
    void testAddition_InchesPlusFeet() {
        QuantityLength result =
                new QuantityLength(12.0, LengthUnit.INCHES)
                        .add(new QuantityLength(1.0, LengthUnit.FEET));

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    //  Yard + feet
    @Test
    void testAddition_YardPlusFeet() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .add(new QuantityLength(3.0, LengthUnit.FEET));

        assertEquals(new QuantityLength(2.0, LengthUnit.YARDS), result);
    }

    //  Centimeter + inch
    @Test
    void testAddition_CentimeterPlusInch() {
        QuantityLength result =
                new QuantityLength(2.54, LengthUnit.CENTIMETERS)
                        .add(new QuantityLength(1.0, LengthUnit.INCHES));

        assertEquals(
                new QuantityLength(5.08, LengthUnit.CENTIMETERS),
                result
        );
    }

    //  Add zero
    @Test
    void testAddition_WithZero() {
        QuantityLength result =
                new QuantityLength(5.0, LengthUnit.FEET)
                        .add(new QuantityLength(0.0, LengthUnit.INCHES));

        assertEquals(new QuantityLength(5.0, LengthUnit.FEET), result);
    }

    //  Negative values
    @Test
    void testAddition_NegativeValues() {
        QuantityLength result =
                new QuantityLength(5.0, LengthUnit.FEET)
                        .add(new QuantityLength(-2.0, LengthUnit.FEET));

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    //  Commutativity
    @Test
    void testAddition_Commutativity() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength r1 = a.add(b);
        QuantityLength r2 = b.add(a);

        assertEquals(r1, r2);
    }

    // Null operand
    @Test
    void testAddition_NullOperand_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, LengthUnit.FEET).add(null));
    }

    //  Large values
    @Test
    void testAddition_LargeValues() {
        QuantityLength result =
                new QuantityLength(1e6, LengthUnit.FEET)
                        .add(new QuantityLength(1e6, LengthUnit.FEET));

        assertEquals(new QuantityLength(2e6, LengthUnit.FEET), result);
    }

    //  Small values
    @Test
    void testAddition_SmallValues() {
        QuantityLength result =
                new QuantityLength(0.001, LengthUnit.FEET)
                        .add(new QuantityLength(0.002, LengthUnit.FEET));

        assertEquals(new QuantityLength(0.003, LengthUnit.FEET), result);
    }
}