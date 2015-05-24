package jumpingalien.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.ObjectType;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsJumping<T extends ObjectType<?>> extends CheckerExpression<T> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsJumping(Expression<T> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public BoolType evaluateLegalCase(Program program) {
		GameObject gameObject = ((GameObjectType) this.getExpression().evaluateLegalCase(program)).getValue();
		return new BoolType(gameObject.isJumping());
	}

	@Override
	public boolean checkType(Expression<T> expression) {
		return expression.getType() instanceof GameObjectType;
	}
}
