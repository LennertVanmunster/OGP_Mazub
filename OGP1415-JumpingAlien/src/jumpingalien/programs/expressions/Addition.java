/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.DoubleType;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class Addition extends BinaryOperation {

	public Addition(Expression left, Expression right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	public Type getType(){
		return new DoubleType();
	}

}