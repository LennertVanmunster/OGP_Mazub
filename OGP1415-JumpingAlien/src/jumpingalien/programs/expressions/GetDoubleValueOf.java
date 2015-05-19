package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.DoubleType;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class GetDoubleValueOf extends Expression {

	public GetDoubleValueOf(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	public GetDoubleValueOf(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Type getType() {
		return new DoubleType();
	}

}
