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
public class GetX extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public GetX(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return new DoubleType();
	}

}
