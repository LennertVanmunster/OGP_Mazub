/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

/**
 * @author Pieter
 *
 */
public class And extends BinaryOperation {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public And(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

}
