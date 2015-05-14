/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

/**
 * @author Pieter
 *
 */
public class Division extends BinaryOperation {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public Division(Expression left, Expression right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

}
