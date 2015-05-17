/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.Type;
import jumpingalien.util.Util;

/**
 * @author Pieter
 *
 */
public class LessThanOrEqualTo extends BinaryOperation {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public LessThanOrEqualTo(Expression left, Expression right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return new BoolType();
	}

	@Override
	public Object evaluate(Program program) {
		System.out.print("Expressie links: " + this.getExpressionLeft().evaluate(program)
				+ " Expressie rechts: " + this.getExpressionRight().evaluate(program));
		if(this.getExpressionLeft().evaluate(program) != null && this.getExpressionRight().evaluate(program) != null){
		return Util.fuzzyLessThanOrEqualTo(((double)this.getExpressionLeft().evaluate(program)), 
				(double)this.getExpressionRight().evaluate(program));
		}
		else
			return false;
	}

}
