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
	    
	    // Volume Unit
	    @Test
	    void testEquality_LitreToMillilitre() {
	        assertEquals(
	                new Quantity<>(1.0, VolumeUnit.LITRE),
	                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
	        );
	    }

	    @Test
	    void testEquality_LitreToGallon() {
	        assertEquals(
	            new Quantity<>(1.0, VolumeUnit.LITRE),
	            new Quantity<>(0.264172, VolumeUnit.GALLON)
	        );
	    }
	    
	    //conversion
	    @Test
	    void testConversion_LitreToMillilitre() {
	        assertEquals(
	                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
	                new Quantity<>(1.0, VolumeUnit.LITRE)
	                        .convertTo(VolumeUnit.MILLILITRE)
	        );
	    }

	    @Test
	    void testConversion_LitreToGallon() {
	        assertEquals(
	                new Quantity<>(0.26, VolumeUnit.GALLON),
	                new Quantity<>(1.0, VolumeUnit.LITRE)
	                        .convertTo(VolumeUnit.GALLON)
	        );
	    }

	    @Test
	    void testConversion_GallonToLitre() {
	        assertEquals(
	                new Quantity<>(3.79, VolumeUnit.LITRE),
	                new Quantity<>(1.0, VolumeUnit.GALLON)
	                        .convertTo(VolumeUnit.LITRE)
	        );
	    }
	    
	    //addition
	    @Test
	    void testAddition_LitrePlusMillilitre() {
	        assertEquals(
	                new Quantity<>(2.0, VolumeUnit.LITRE),
	                new Quantity<>(1.0, VolumeUnit.LITRE)
	                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE))
	        );
	    }

	    @Test
	    void testAddition_ExplicitTargetUnit_Gallon() {
	        assertEquals(
	                new Quantity<>(2.0, VolumeUnit.GALLON),
	                new Quantity<>(1.0, VolumeUnit.GALLON)
	                        .add(new Quantity<>(3.78541, VolumeUnit.LITRE),
	                             VolumeUnit.GALLON)
	        );
	    }
	    
	    @Test
	    void testEquality_LitreToLitre_SameValue() {
	        assertEquals(
	                new Quantity<>(1.0, VolumeUnit.LITRE),
	                new Quantity<>(1.0, VolumeUnit.LITRE)
	        );
	    }

	    @Test
	    void testEquality_LitreToLitre_DifferentValue() {
	        assertNotEquals(
	                new Quantity<>(1.0, VolumeUnit.LITRE),
	                new Quantity<>(2.0, VolumeUnit.LITRE)
	        );
	    }

	    @Test
	    void testEquality_MillilitreToMillilitre() {
	        assertEquals(
	                new Quantity<>(500.0, VolumeUnit.MILLILITRE),
	                new Quantity<>(500.0, VolumeUnit.MILLILITRE)
	        );
	    }

	    @Test
	    void testEquality_GallonToGallon() {
	        assertEquals(
	                new Quantity<>(2.0, VolumeUnit.GALLON),
	                new Quantity<>(2.0, VolumeUnit.GALLON)
	        );
	    }


	    @Test
	    void testEquality_LitreToMillilitre_Equivalent() {
	        assertEquals(
	                new Quantity<>(1.0, VolumeUnit.LITRE),
	                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
	        );
	    }

	    @Test
	    void testEquality_MillilitreToLitre_Equivalent() {
	        assertEquals(
	                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
	                new Quantity<>(1.0, VolumeUnit.LITRE)
	        );
	    }

	    @Test
	    void testEquality_LitreToGallon_Equivalent() {
	        assertEquals(
	                new Quantity<>(1.0, VolumeUnit.LITRE),
	                new Quantity<>(0.264172, VolumeUnit.GALLON)
	        );
	    }

	    @Test
	    void testEquality_GallonToLitre_Equivalent() {
	        assertEquals(
	                new Quantity<>(1.0, VolumeUnit.GALLON),
	                new Quantity<>(3.78541, VolumeUnit.LITRE)
	        );
	    }


	    @Test
	    void testEquality_VolumeVsLength() {
	        assertNotEquals(
	                new Quantity<>(1.0, VolumeUnit.LITRE),
	                new Quantity<>(1.0, LengthUnit.FEET)
	        );
	    }

	    @Test
	    void testEquality_VolumeVsWeight() {
	        assertNotEquals(
	                new Quantity<>(1.0, VolumeUnit.LITRE),
	                new Quantity<>(1.0, WeightUnit.KILOGRAM)
	        );
	    }


	    @Test
	    void testConversion_MillilitreToLitre() {
	        assertEquals(
	                new Quantity<>(1.0, VolumeUnit.LITRE),
	                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
	                        .convertTo(VolumeUnit.LITRE)
	        );
	    }


	    @Test
	    void testAddition_SameUnit_Litre() {
	        assertEquals(
	                new Quantity<>(3.0, VolumeUnit.LITRE),
	                new Quantity<>(1.0, VolumeUnit.LITRE)
	                        .add(new Quantity<>(2.0, VolumeUnit.LITRE))
	        );
	    }

	    @Test
	    void testAddition_CrossUnit_LitrePlusMillilitre() {
	        assertEquals(
	                new Quantity<>(2.0, VolumeUnit.LITRE),
	                new Quantity<>(1.0, VolumeUnit.LITRE)
	                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE))
	        );
	    }

	    @Test
	    void testAddition_ExplicitTargetUnit_Millilitre() {
	        assertEquals(
	                new Quantity<>(2000.0, VolumeUnit.MILLILITRE),
	                new Quantity<>(1.0, VolumeUnit.LITRE)
	                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
	                             VolumeUnit.MILLILITRE)
	        );
	    }

	    @Test
	    void testAddition_GallonPlusLitre() {
	        assertEquals(
	                new Quantity<>(2.0, VolumeUnit.GALLON),
	                new Quantity<>(1.0, VolumeUnit.GALLON)
	                        .add(new Quantity<>(3.78541, VolumeUnit.LITRE))
	        );
	    }

	    

	    @Test
	    void testHashCode_Consistency() {
	        Quantity<VolumeUnit> a =
	                new Quantity<>(1.0, VolumeUnit.LITRE);
	        Quantity<VolumeUnit> b =
	                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

	        assertEquals(a.hashCode(), b.hashCode());
	    }

	    
	    @Test
	    void testConstructor_NaNValue() {
	        assertThrows(IllegalArgumentException.class,
	                () -> new Quantity<>(Double.NaN, VolumeUnit.LITRE));
	    }

	    @Test
	    void testConstructor_InfiniteValue() {
	        assertThrows(IllegalArgumentException.class,
	                () -> new Quantity<>(Double.POSITIVE_INFINITY, VolumeUnit.LITRE));
	    }
}