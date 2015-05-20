package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class CheckerExpression extends UnaryOperation<BoolType, ObjectType<?>> {

	public CheckerExpression(Expression<ObjectType<?>> expression,SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	public CheckerExpression(SourceLocation sourceLocation) {
		this(null, sourceLocation);
	}

	@Override
	public Type<?> getType() {
		return new BoolType();
	}
	


}
