package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.DoubleType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class MathematicalExpression extends BinaryOperation<DoubleType, DoubleType> {

	public MathematicalExpression(Expression<DoubleType> left, Expression<DoubleType> right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	
	
	@Override
	public DoubleType getType(){
		return new DoubleType();
	}
	
	@Override
	public boolean checkType(Expression<DoubleType> expression) {
		return expression.getType() instanceof DoubleType;
	}


}
