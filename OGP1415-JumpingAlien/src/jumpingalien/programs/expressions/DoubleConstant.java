package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

public class DoubleConstant extends Expression {


	/**
	 * @param value
	 * @param sourceLocation 
	 */
	public DoubleConstant(double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
		this.setSourceLocation(sourceLocation);
	}
	
	
	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}

	private double value;
}
