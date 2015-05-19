package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class Comparison extends BinaryOperation {

	public Comparison(Expression left, Expression right,SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public Type getType() {
		return new BoolType();
	}

}
