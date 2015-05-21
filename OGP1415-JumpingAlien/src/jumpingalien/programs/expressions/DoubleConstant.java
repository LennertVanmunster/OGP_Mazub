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
	
	public DoubleConstant(double value) {
		this(value, null);

	}
	

	public double getValue() {
		return value;
	}


	private void setValue(double value) {
		this.value = value;
	}

	private double value;
	
	@Override
	public DoubleType evaluate(Program program){
		if(this.getStopProgram() || program.hasStopped()){
			program.stop();
			return null;
		}
		return new DoubleType(this.getValue());
	}
	
	@Override
	public Type<?> getType(){
		return new DoubleType();
	}
}
