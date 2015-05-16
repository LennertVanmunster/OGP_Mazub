/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class IsDead extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsDead(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return new BoolType();
	}


	@Override
	public Object evaluate(Program program) {
		return ((GameObject) this.getExpression().evaluate(program)).isTerminated();
	}

}
