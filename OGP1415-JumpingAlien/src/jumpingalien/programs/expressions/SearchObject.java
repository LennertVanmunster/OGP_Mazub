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
public class SearchObject extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public SearchObject(Expression direction, SourceLocation sourceLocation) {
		super(direction, sourceLocation);
	}


	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return new ObjectType();
	}

}
