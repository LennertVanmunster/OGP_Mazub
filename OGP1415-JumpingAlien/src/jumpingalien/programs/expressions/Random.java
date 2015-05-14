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
public class Random extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public Random(Expression maxvalue, SourceLocation sourceLocation) {
		super(maxvalue, sourceLocation);
		// TODO Auto-generated constructor stub
	}

	public Type getType(){
		return new DoubleType();
	}

}
