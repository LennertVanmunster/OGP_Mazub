/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

/**
 * @author Pieter
 *
 */
public class Or extends BinaryOperation {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public Or(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

}
