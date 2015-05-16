/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class IsMazub extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsMazub(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return BoolType();
	}

	@Override
	public Object evaluate(Program program) {
		return this.getExpression().evaluate(program) instanceof Mazub;
	}

}
