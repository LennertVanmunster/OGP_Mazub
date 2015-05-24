package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.DoubleType;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class GetDoubleValueOf extends UnaryOperation<DoubleType, GameObjectType> {

	public GetDoubleValueOf(Expression<GameObjectType> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}
	
	@Override
	public DoubleType getType() {
		return new DoubleType();
	}
	
	@Override
	public boolean checkType(Expression<GameObjectType> expression) {
		return expression.getType() instanceof GameObjectType;
	}

}
