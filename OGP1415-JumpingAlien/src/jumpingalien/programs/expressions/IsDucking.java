package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsDucking<T extends ObjectType<?>> extends CheckerExpression<T> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsDucking(Expression<T> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public BoolType evaluateLegalCase(Program program) {
		ObjectType<?> object = (ObjectType<?>) this.getExpression().evaluateLegalCase(program);
		if (object instanceof TileType){
			return new BoolType(false);
		}
		GameObjectType gameObject = (GameObjectType) object;
		return new BoolType(gameObject.getValue().isDucking());
	}
	
	@Override
	public boolean checkType(Expression<T> expression) {
		return expression.getType() instanceof GameObjectType;
	}

}
