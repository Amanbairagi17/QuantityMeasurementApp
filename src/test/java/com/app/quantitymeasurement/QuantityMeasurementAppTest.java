package com.app.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {
	    private static final double EPS = 0.00001;

	    //   BASIC EQUALITY

	    @Test
	    void testEquality_KilogramToKilogram_SameValue() {
	        assertEquals(
	                new Quantity<>(1.0, WeightUnit.KILOGRAM),
	                new Quantity<>(1.0, WeightUnit.KILOGRAM)
	        );
	    }

	    @Test
	    void testEquality_KilogramToKilogram_DifferentValue() {
	        assertNotEquals(
	                new Quantity<>(1.0, WeightUnit.KILOGRAM),
	                new Quantity<>(2.0, WeightUnit.KILOGRAM)
	        );
	    }

	    @Test
	    void testEquality_GramToGram() {
	        assertEquals(
	                new Quantity<>(500.0, WeightUnit.GRAM),
	                new Quantity<>(500.0, WeightUnit.GRAM)
	        );
	    }

	    @Test
	    void testEquality_PoundToPound() {
	        assertEquals(
	                new Quantity<>(2.0, WeightUnit.POUND),
	                new Quantity<>(2.0, WeightUnit.POUND)
	        );
	    }

	    //   CROSS-UNIT EQUALITY
	    @Test
	    void testEquality_KilogramToGram() {
	        assertEquals(
	                new Quantity<>(1.0, WeightUnit.KILOGRAM),
	                new Quantity<>(1000.0, WeightUnit.GRAM)
	        );
	    }

	    @Test
	    void testEquality_KilogramToPound() {
	        assertEquals(
	                new Quantity<>(1.0, WeightUnit.KILOGRAM),
	                new Quantity<>(2.20462262, WeightUnit.POUND)
	        );
	    }

	    @Test
	    void testEquality_GramToPound() {
	        assertEquals(
	                new Quantity<>(453.592, WeightUnit.GRAM),
	                new Quantity<>(1.0, WeightUnit.POUND)
	        );
	    }

	    //   CATEGORY SAFETY
	    @Test
	    void testEquality_WeightVsLength_Incompatible() {
	        Quantity<WeightUnit> weight =
	                new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        Quantity<LengthUnit> length =
	                new Quantity<>(1.0, LengthUnit.FEET);

	        assertNotEquals(weight, length);
	    }

	     //  NULL & IDENTITY
	    @Test
	    void testEquality_NullComparison() {
	        Quantity<WeightUnit> q =
	                new Quantity<>(1.0, WeightUnit.KILOGRAM);

	        assertNotEquals(q, null);
	    }

	    @Test
	    void testEquality_SameReference() {
	        Quantity<WeightUnit> q =
	                new Quantity<>(1.0, WeightUnit.KILOGRAM);

	        assertEquals(q, q);
	    }

	    @Test
	    void testConstructor_NullUnit() {
	        assertThrows(
	                IllegalArgumentException.class,
	                () -> new Quantity<>(1.0, null)
	        );
	    }

	      // TRANSITIVE & SYMMETRIC

	    @Test
	    void testEquality_TransitiveProperty() {
	        Quantity<WeightUnit> a =
	                new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        Quantity<WeightUnit> b =
	                new Quantity<>(1000.0, WeightUnit.GRAM);
	        Quantity<WeightUnit> c =
	                new Quantity<>(2.20462262, WeightUnit.POUND);

	        assertEquals(a, b);
	        assertEquals(b, c);
	        assertEquals(a, c);
	    }

	     //  EDGE CASES

	    @Test
	    void testEquality_ZeroValue() {
	        assertEquals(
	                new Quantity<>(0.0, WeightUnit.KILOGRAM),
	                new Quantity<>(0.0, WeightUnit.GRAM)
	        );
	    }

	    @Test
	    void testEquality_NegativeValue() {
	        assertEquals(
	                new Quantity<>(-1.0, WeightUnit.KILOGRAM),
	                new Quantity<>(-1000.0, WeightUnit.GRAM)
	        );
	    }

	    @Test
	    void testEquality_LargeValue() {
	        assertEquals(
	                new Quantity<>(1_000_000.0, WeightUnit.GRAM),
	                new Quantity<>(1000.0, WeightUnit.KILOGRAM)
	        );
	    }

	    @Test
	    void testEquality_SmallValue() {
	        assertEquals(
	                new Quantity<>(0.001, WeightUnit.KILOGRAM),
	                new Quantity<>(1.0, WeightUnit.GRAM)
	        );
	    }

	    //   CONVERSION

	    @Test
	    void testConversion_PoundToKilogram() {
	        Quantity<WeightUnit> result =
	                new Quantity<>(2.20462262, WeightUnit.POUND)
	                        .convertTo(WeightUnit.KILOGRAM);

	        assertEquals(
	                new Quantity<>(1.0, WeightUnit.KILOGRAM),
	                result
	        );
	    }

	    @Test
	    void testConversion_KilogramToPound() {
	        Quantity<WeightUnit> result =
	                new Quantity<>(1.0, WeightUnit.KILOGRAM)
	                        .convertTo(WeightUnit.POUND);

	        assertEquals(
	                new Quantity<>(2.20, WeightUnit.POUND),
	                result
	        );
	    }

	    @Test
	    void testConversion_SameUnit() {
	        Quantity<WeightUnit> q =
	                new Quantity<>(5.0, WeightUnit.KILOGRAM);

	        assertEquals(q, q.convertTo(WeightUnit.KILOGRAM));
	    }

	    @Test
	    void testConversion_RoundTrip() {
	        Quantity<WeightUnit> original =
	                new Quantity<>(1.5, WeightUnit.KILOGRAM);

	        Quantity<WeightUnit> roundTrip =
	                original.convertTo(WeightUnit.GRAM)
	                        .convertTo(WeightUnit.KILOGRAM);

	        assertEquals(original, roundTrip);
	    }

	     //  ADDITION

	    @Test
	    void testAddition_SameUnit() {
	        assertEquals(
	                new Quantity<>(3.0, WeightUnit.KILOGRAM),
	                new Quantity<>(1.0, WeightUnit.KILOGRAM)
	                        .add(new Quantity<>(2.0, WeightUnit.KILOGRAM))
	        );
	    }

	    @Test
	    void testAddition_CrossUnit() {
	        assertEquals(
	                new Quantity<>(2.0, WeightUnit.KILOGRAM),
	                new Quantity<>(1.0, WeightUnit.KILOGRAM)
	                        .add(new Quantity<>(1000.0, WeightUnit.GRAM))
	        );
	    }

	    @Test
	    void testAddition_ExplicitTargetUnit() {
	        assertEquals(
	                new Quantity<>(2000.0, WeightUnit.GRAM),
	                new Quantity<>(1.0, WeightUnit.KILOGRAM)
	                        .add(new Quantity<>(1000.0, WeightUnit.GRAM),
	                             WeightUnit.GRAM)
	        );
	    }

	    @Test
	    void testAddition_Commutativity() {
	        Quantity<WeightUnit> a =
	                new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        Quantity<WeightUnit> b =
	                new Quantity<>(1000.0, WeightUnit.GRAM);

	        assertEquals(a.add(b), b.add(a));
	    }

	    @Test
	    void testAddition_WithZero() {
	        assertEquals(
	                new Quantity<>(5.0, WeightUnit.KILOGRAM),
	                new Quantity<>(5.0, WeightUnit.KILOGRAM)
	                        .add(new Quantity<>(0.0, WeightUnit.GRAM))
	        );
	    }

	    @Test
	    void testAddition_Negative() {
	        assertEquals(
	                new Quantity<>(3.0, WeightUnit.KILOGRAM),
	                new Quantity<>(5.0, WeightUnit.KILOGRAM)
	                        .add(new Quantity<>(-2000.0, WeightUnit.GRAM))
	        );
	    }

	    @Test
	    void testAddition_LargeValues() {
	        assertEquals(
	                new Quantity<>(2_000_000.0, WeightUnit.KILOGRAM),
	                new Quantity<>(1_000_000.0, WeightUnit.KILOGRAM)
	                        .add(new Quantity<>(1_000_000.0,
	                                             WeightUnit.KILOGRAM))
	        );
	    }


	    @Test
	    void testHashCodeConsistency() {
	        Quantity<WeightUnit> a =
	                new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        Quantity<WeightUnit> b =
	                new Quantity<>(1000.0, WeightUnit.GRAM);

	        assertEquals(a.hashCode(), b.hashCode());
	    }
}