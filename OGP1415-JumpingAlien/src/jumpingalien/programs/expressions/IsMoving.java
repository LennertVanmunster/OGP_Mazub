/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
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
	public void setDirection(Expression direction) {
		this.direction = direction;
	}

	private Expression direction;

	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return new BoolType();
	}

	@Override
	public Object evaluate(Program program) {
		GameObject gameObject = ((GameObject)this.getExpression().evaluate(program));
		return gameObject.isMovingHorizontally() && gameObject.getDirection() == this.getDirection().evaluate(program);
				
	}

}
