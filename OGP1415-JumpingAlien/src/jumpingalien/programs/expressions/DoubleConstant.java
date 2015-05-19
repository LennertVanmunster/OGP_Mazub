package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.*;

import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
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
		this(value, null);

	}
	

	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}

	private double value;
	
	@Override
	public Double evaluate(Program program){
		return (double)this.getValue();
	}
	
	@Override
	public Type getType(){
		return new DoubleType();
	}
}
