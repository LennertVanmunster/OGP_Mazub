package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

import jumpingalien.programs.types.*;

public class DoubleConstant extends Expression {


	/**
	 * @param value
	 * @param sourceLocation 
	 */
	public DoubleConstant(double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	public DoubleConstant(double value) {
		this(value, new SourceLocation(0,0));
	}
	
	
	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}

	private double value;
	
	public double evaluate(){
		return this.getValue();
	}
	
	public Type getType(){
		return new DoubleType();
	}
}
