package jumpingalien.programs.expressions;

import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.ObjectType;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsShark extends CheckerExpression<GameObjectType> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsShark(Expression<GameObjectType> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public BoolType evaluateLegalCase(Program program) {
		return new BoolType(((GameObjectType) this.getExpression().evaluateLegalCase(program)).getValue() instanceof Shark);
	}


	@Override
	public boolean checkType(Expression<GameObjectType> expression) {
		return expression.getType() instanceof GameObjectType;
	}

}
