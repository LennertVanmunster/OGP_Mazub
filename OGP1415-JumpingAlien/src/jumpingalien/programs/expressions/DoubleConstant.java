package jumpingalien.programs.expressions;

import jumpingalien.programs.types.*;

public class DoubleConstant extends Expression {


	/**
	 * @param value
	 */
	public DoubleConstant(double value) {
		this.value = value;
	}
	
	public Type getType(){
		return new DoubleType();
	}
	
	
	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}


	private double value;
}
