/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.*;

/**
 * @author Pieter
 *
 */
public class Sqrt extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public Sqrt(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}
	
	public Type getType(){
		return new DoubleType();
	}

}
