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
public class And extends BinaryOperation {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public And(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	public Type getType(){
		return new BoolType();
	}

	@Override
	public Object evaluate(Program program) {
		return (boolean) this.getExpressionLeft().evaluate(program) &&
				(boolean) this.getExpressionRight().evaluate(program);
	}

}
