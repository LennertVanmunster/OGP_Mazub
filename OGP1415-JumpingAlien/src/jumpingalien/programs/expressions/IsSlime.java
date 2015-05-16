/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
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
		// TODO Auto-generated constructor stub
	}


	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return new BoolType();
	}

}
