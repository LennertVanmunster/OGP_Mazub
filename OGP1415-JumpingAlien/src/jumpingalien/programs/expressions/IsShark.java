/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class IsShark extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsShark(Expression expression, SourceLocation sourceLocation) {
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
		return this.getExpression().evaluate(program) instanceof Shark;
	}

}
