package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class BooleanOperation extends BinaryOperation<BoolType, BoolType> {

	public BooleanOperation(Expression<BoolType> left, Expression<BoolType> right,SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public BoolType getType(){
		return new BoolType();
	}
	
	@Override
	public boolean checkType(Expression<BoolType> expression) {
		return expression.getType() instanceof BoolType;
	}


}
