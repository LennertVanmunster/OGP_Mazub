/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.model.Slime;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class IsSlime extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsSlime(Expression expression, SourceLocation sourceLocation) {
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
		return this.getExpression().evaluate(program) instanceof Slime;
	}

}
