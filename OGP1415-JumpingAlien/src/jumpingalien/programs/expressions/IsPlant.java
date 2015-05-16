/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class IsPlant extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsPlant(Expression expression, SourceLocation sourceLocation) {
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
		return this.getExpression().evaluate(program) instanceof Plant;
	}

}
