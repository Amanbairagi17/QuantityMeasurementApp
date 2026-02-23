package com.app.quantitymeasurement;

import java.util.Objects;

public class QuantityLength {
	private final double value;
	private final LengthUnit unit;
	
	public QuantityLength(double value, LengthUnit unit) {
		if (unit == null) {
	        throw new IllegalArgumentException("Unit cannot be null");
	    }
		this.value = value;
		this.unit = unit;
	}
	
	public double valueInFeet() {
		return unit.toFeet(value);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null) return false;
	    if (!(obj instanceof QuantityLength)) return false;

	    QuantityLength other = (QuantityLength) obj;
	    return Double.compare(this.valueInFeet(), other.valueInFeet()) == 0;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(valueInFeet());
	}
	
	
	
	
}
