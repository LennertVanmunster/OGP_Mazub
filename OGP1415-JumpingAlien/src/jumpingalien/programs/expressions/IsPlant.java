package jumpingalien.programs.expressions;

import jumpingalien.model.Plant;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.ObjectType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsPlant extends CheckerExpression<GameObjectType> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsPlant(Expression<GameObjectType> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public BoolType evaluateLegalCase(Program program) {
		return new BoolType(((GameObjectType) this.getExpression().evaluateLegalCase(program)).getValue() instanceof Plant);
	}


	@Override
	public boolean checkType(Expression<GameObjectType> expression) {
		return expression.getType() instanceof GameObjectType;
	}

}
