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
	    void testConversion_SameUnit() {
	        Quantity<WeightUnit> q =
	                new Quantity<>(5.0, WeightUnit.KILOGRAM);

	        assertEquals(q, q.convertTo(WeightUnit.KILOGRAM));
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
	    
	    @Test
	    public void lengthFeetEqualsInches() {
	        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
	        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

	        assertTrue(feet.equals(inches));
	    }

	    @Test
	    public void lengthYardsEqualsFeet() {
	        Quantity<LengthUnit> yards = new Quantity<>(1.0, LengthUnit.YARDS);
	        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);

	        assertTrue(yards.equals(feet));
	    }

	    @Test
	    public void weightKilogramEqualsGrams() {
	        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);

	        assertTrue(kg.equals(grams));
	    }

	    @Test
	    public void weightPoundEqualsGrams() {
	        Quantity<WeightUnit> pound = new Quantity<>(1.0, WeightUnit.POUND);
	        Quantity<WeightUnit> grams = new Quantity<>(453.592, WeightUnit.GRAM);

	        assertTrue(pound.equals(grams));
	    }


	    @Test
	    public void testGenericTypeSafetyWithWeight() {
	        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        
	        assertEquals(1.0, weight.getValue());
	        assertEquals(WeightUnit.KILOGRAM, weight.getUnit());
	    }

	   

	    @Test
	    public void preventCrossTypeComparisonLengthVsWeight() {
	        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
	        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

	        assertFalse(length.equals(weight));
	    }

	    @Test
	    public void preventCrossTypeAdditionLengthVsWeight() {
	        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

	        assertThrows(IllegalArgumentException.class, () -> {
	            length.add((Quantity)new Quantity<WeightUnit>(1.0, WeightUnit.KILOGRAM));
	        });
	    }

	    @Test
	    public void preventCrossTypeConversionLengthToWeight() {
	        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
	        
	        assertThrows(Exception.class, () -> {
	            length.convertTo((LengthUnit) (Object) WeightUnit.GRAM);
	        });
	    }

	    
	    
	    @Test
	    public void backwardCompatibilityLengthFeetEqualsInches() {
	        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
	        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

	        assertTrue(feet.equals(inches));
	    }

	    @Test
	    public void backwardCompatibilityWeightKilogramEqualsGrams() {
	        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);

	        assertTrue(kg.equals(grams));
	    }

	   

	    @Test
	    public void backwardCompatibilityAddLengthInSameUnit() {
	        Quantity<LengthUnit> feet1 = new Quantity<>(2.0, LengthUnit.FEET);
	        Quantity<LengthUnit> feet2 = new Quantity<>(3.0, LengthUnit.FEET);
	        Quantity<LengthUnit> result = feet1.add(feet2);

	        assertEquals(5.0, result.getValue());
	    }

	    @Test
	    public void backwardCompatibilityAddWeightInSameUnit() {
	        Quantity<WeightUnit> grams1 = new Quantity<>(500.0, WeightUnit.GRAM);
	        Quantity<WeightUnit> grams2 = new Quantity<>(500.0, WeightUnit.GRAM);
	        Quantity<WeightUnit> result = grams1.add(grams2);

	        assertEquals(1000.0, result.getValue());
	    }

	    @Test
	    public void backwardCompatibilityLengthYardsEqualsFeet() {
	        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
	        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);

	        assertTrue(yard.equals(feet));
	    }

	    @Test
	    public void backwardCompatibilityWeightPoundEqualsGrams() {
	        Quantity<WeightUnit> pound = new Quantity<>(1.0, WeightUnit.POUND);
	        Quantity<WeightUnit> grams = new Quantity<>(453.592, WeightUnit.GRAM);

	        assertTrue(pound.equals(grams));
	    }

	    @Test
	    public void backwardCompatibilityChainedAdditionsLength() {
	        Quantity<LengthUnit> oneFoot = new Quantity<>(1.0, LengthUnit.FEET);
	        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);
	        Quantity<LengthUnit> oneYard = new Quantity<>(1.0, LengthUnit.YARDS);
	        Quantity<LengthUnit> result = oneFoot.add(twelveInches).add(oneYard);

	        assertEquals(5.0, result.getValue());
	    }
	    
	    @Test
	    public void volumeLitreEqualsMillilitre() {
	        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
	        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

	        assertTrue(litre.equals(ml));
	    }

	    @Test
	    public void volumeNotEqual() {
	        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
	        Quantity<VolumeUnit> twoLitres = new Quantity<>(2.0, VolumeUnit.LITRE);

	        assertFalse(litre.equals(twoLitres));
	    }
	    
	    

	    
	    
	    @Test
	    public void addVolumeSameUnit() {
	        Quantity<VolumeUnit> one = new Quantity<>(1.0, VolumeUnit.LITRE);
	        Quantity<VolumeUnit> two = new Quantity<>(2.0, VolumeUnit.LITRE);

	        Quantity<VolumeUnit> result = one.add(two);

	        assertEquals(3.0, result.getValue());
	    }

	    @Test
	    public void addVolumeDifferentUnits() {
	        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
	        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

	        Quantity<VolumeUnit> result = litre.add(ml);

	        assertEquals(2.0, result.getValue());
	    }

	    @Test
	    public void addVolumeWithTargetUnit() {
	    	Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
	        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

	        Quantity<VolumeUnit> result = litre.add(ml, VolumeUnit.MILLILITRE);

	        assertEquals(2000.0, result.getValue());
	    }
	    
	    @Test
	    public void preventVolumeVsLengthComparison() {
	        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
	        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
	        
	        assertFalse(volume.equals(length));
	    }
	    
	    @Test
	    public void subtractSameUnit() {
	        Quantity<LengthUnit> ten = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> five = new Quantity<>(5.0, LengthUnit.FEET);
	        Quantity<LengthUnit> result = ten.subtract(five);

	        assertEquals(5.0, result.getValue());
	    }

	    @Test
	    public void subtractCrossUnit() {
	        Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> sixInches = new Quantity<>(6.0, LengthUnit.INCHES);
	        Quantity<LengthUnit> result = tenFeet.subtract(sixInches);

	        assertEquals(9.5, result.getValue());
	    }

	    @Test
	    public void subtractExplicitTargetUnit() {
	        Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> sixInches = new Quantity<>(6.0, LengthUnit.INCHES);
	        Quantity<LengthUnit> result = tenFeet.subtract(sixInches, LengthUnit.INCHES);

	        assertEquals(114.0, result.getValue());
	    }

	    @Test
	    public void subtractNegativeResult() {
	        Quantity<LengthUnit> five = new Quantity<>(5.0, LengthUnit.FEET);
	        Quantity<LengthUnit> ten = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> result = five.subtract(ten);

	        assertEquals(-5.0, result.getValue());
	    }


	    @Test
	    public void divideByZeroThrowsException() {
	        Quantity<LengthUnit> ten = new Quantity<>(10.0, LengthUnit.FEET);
	        Quantity<LengthUnit> zero = new Quantity<>(0.0, LengthUnit.FEET);

	        assertThrows(IllegalArgumentException.class, () -> {
	            ten.divide(zero);
	        });
	    }
	    
}