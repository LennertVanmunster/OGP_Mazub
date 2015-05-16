/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
 * @author Pieter
 *
 */
public class Multiplication extends BinaryOperation {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public Multiplication(Expression left, Expression right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	

	
	public Type getType(){
		return new DoubleType();
	}



	@Override
	public Object evaluate(Program program) {
		return (double)this.getExpressionLeft().evaluate(program) * 
		(double)this.getExpressionRight().evaluate(program);
	}

}
