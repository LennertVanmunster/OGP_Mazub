package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.*;

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
//		this(value, new SourceLocation(0,0));
		this(value, null);

	}
	
	
	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}

	private double value;
	
	public Object evaluate(Program program){
		return (double)this.getValue();
	}
	
	public Type getType(){
		return new DoubleType();
	}
}
