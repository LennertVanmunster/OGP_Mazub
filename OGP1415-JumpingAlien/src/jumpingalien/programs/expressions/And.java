package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class And extends BooleanOperation {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public And(Expression<BoolType> left, Expression<BoolType> right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public BoolType evaluateLegalCase(Program program) {
		return new BoolType(((BoolType) this.getExpressionLeft().evaluateLegalCase(program)).getValue() &&
				((BoolType)this.getExpressionRight().evaluateLegalCase(program)).getValue());
	}

}
