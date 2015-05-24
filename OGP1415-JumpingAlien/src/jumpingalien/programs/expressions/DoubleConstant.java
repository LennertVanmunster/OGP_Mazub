package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.*;

import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class DoubleConstant extends Expression<DoubleType> {


	/**
	 * @param value
	 * @param sourceLocation 
	 */
	public DoubleConstant(double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setValue(value);
	}

	public double getValue() {
		return value;
	}


	private void setValue(double value) {
		this.value = value;
	}

	private double value;
	
	@Override
	public DoubleType evaluateLegalCase(Program program){
		return new DoubleType(this.getValue());
	}
	
	@Override
	public DoubleType getType(){
		return new DoubleType();
	}
}
