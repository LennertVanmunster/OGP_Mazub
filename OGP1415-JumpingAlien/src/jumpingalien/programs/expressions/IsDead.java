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
public class IsDead extends CheckerExpression<GameObjectType> {

	/**
	 * @param gameObject
	 * @param sourceLocation
	 */
	public IsDead(Expression<GameObjectType> gameObject, SourceLocation sourceLocation) {
		super(gameObject, sourceLocation);
	}


	@Override
	public BoolType evaluateLegalCase(Program program) {
		return new BoolType(((GameObjectType) this.getExpression().evaluateLegalCase(program)).getValue().isTerminated());
	}


	@Override
	public boolean checkType(Expression<GameObjectType> expression) {
		return expression.getType() instanceof GameObjectType;
	}
}
