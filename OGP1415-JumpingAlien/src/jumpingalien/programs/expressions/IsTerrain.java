package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.ObjectType;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsTerrain<T extends ObjectType<?>> extends CheckerExpression<T> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsTerrain(Expression<T> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public BoolType evaluateLegalCase(Program program) {
		ObjectType<?> object = (ObjectType<?>) this.getExpression().evaluateLegalCase(program);
		if (object instanceof GameObjectType){
			return new BoolType(false);
		}
		else{
			return new BoolType(true);
		}
	}


	@Override
	public boolean checkType(Expression<T> expression) {
		return expression.getType() instanceof ObjectType;
	}

}
