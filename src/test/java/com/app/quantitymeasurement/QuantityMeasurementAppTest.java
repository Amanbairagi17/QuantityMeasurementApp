package com.app.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {
	
	private static final double EPSILON = 0.0001;
	 @Test
	    public void testFeetEquality() {
	    	QuantityLength feet1 = new QuantityLength(10.0, LengthUnit.FEET);
	    	QuantityLength feet2 = new QuantityLength(10.0, LengthUnit.FEET);
	    	
	    	assertTrue(feet1.equals(feet2));
	    }
	    
	    @Test
	    public void testInchesEquality() {
	    	QuantityLength inch1 = new QuantityLength(10.0, LengthUnit.INCHES);
	    	QuantityLength inch2 = new QuantityLength(10.0, LengthUnit.INCHES);
	    	
	    	assertTrue(inch1.equals(inch2));
	    }
	    
	    @Test
	    public void testFeetInchesComparison() {
	    	QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
	    	QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCHES);
	    	
	    	assertTrue(feet.equals(inch));
		}
	    
	    @Test
	    public void testFeetInequality() {
	    	QuantityLength feet1 = new QuantityLength(10.0, LengthUnit.FEET);
	    	QuantityLength feet2 = new QuantityLength(20.0, LengthUnit.FEET);
	    	
	    	assertFalse(feet1.equals(feet2));
	    }
	    
	    @Test
	    public void testInchesInequality() {
	    	QuantityLength inch1 = new QuantityLength(10.0, LengthUnit.INCHES);
	    	QuantityLength inch2 = new QuantityLength(20.0, LengthUnit.INCHES);
	    	
	    	assertFalse(inch1.equals(inch2));
	    }
	    
	    @Test
	    public void testCrossUnitInequality() {
	    	QuantityLength feet = new QuantityLength(24.0, LengthUnit.FEET);
	    	QuantityLength inch = new QuantityLength(1.0, LengthUnit.INCHES);
	    	
	    	assertFalse(feet.equals(inch));
		}
	    
	    @Test
	    public void testMultipleFeetComparison() {
	    	QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
	    	QuantityLength inch = new QuantityLength(36.0, LengthUnit.INCHES);

	        assertTrue(feet.equals(inch));
	    }
	    
	    @Test 
	    public void yardEquals36Inches() {
	    	QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
	    	QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCHES);
	    	
	    	assertTrue(yard.equals(inches));
	    }
	    
	    @Test
	    public void centimeterEquals39Point3701Inches() {
	    	QuantityLength centimeter = new QuantityLength(100.0, LengthUnit.CENTIMETERS);
	    	QuantityLength inches = new QuantityLength(39.37, LengthUnit.INCHES);
	    	
	    	assertTrue(centimeter.equals(inches));
	    }
	    
	    @Test
	    public void threeFeetEqualsOneYard() {
	    	QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
	    	QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);

	        assertTrue(feet.equals(yard));
	    }

	    @Test
	    public void thirtyPoint48CmEqualsOneFoot() {
	    	QuantityLength centimeter = new QuantityLength(30.48, LengthUnit.CENTIMETERS);
	    	QuantityLength foot = new QuantityLength(1.0, LengthUnit.FEET);

	        assertTrue(centimeter.equals(foot));
	    }

	    @Test
	    public void yardNotEqualToInches() {
	    	QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
	    	QuantityLength inches = new QuantityLength(10.0, LengthUnit.INCHES);

	        assertFalse(yard.equals(inches));
	    }

	    @Test
	    public void referenceEqualitySameObject() {
	    	QuantityLength length = new QuantityLength(10.0, LengthUnit.FEET);

	        assertTrue(length.equals(length));
	    }

	    @Test
	    public void equalsReturnsFalseForNull() {
	    	QuantityLength length = new QuantityLength(10.0, LengthUnit.FEET);

	        assertFalse(length.equals(null));
	    }
	
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