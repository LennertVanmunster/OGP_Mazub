package jumpingalien.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsMoving extends Expression {


	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsMoving(Expression expression, Expression direction, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		this.setDirection(direction);
	}

	/**
	 * @return the direction
	 */
	public Expression getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	private void setDirection(Expression direction) {
		this.direction = direction;
	}

	private Expression direction;

	
	@Override
	public Type getType() {
		return new BoolType();
	}

	@Override
	public Boolean evaluate(Program program) {
		GameObject gameObject = ((GameObject)this.getExpression().evaluate(program));
		return gameObject.isMovingHorizontally() && gameObject.getDirection() == this.getDirection().evaluate(program);
				
	}

}
