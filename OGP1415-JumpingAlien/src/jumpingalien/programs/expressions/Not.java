package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Not extends UnaryOperation<BoolType, BoolType> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public Not(Expression<BoolType> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}
	
	public BoolType getType(){
		return new BoolType();
	}

	@Override
	public BoolType evaluateLegalCase(Program program) {
		return new BoolType(!((BoolType)this.getExpression().evaluateLegalCase(program)).getValue());
	}

	@Override
	public boolean checkType(Expression<BoolType> expression) {
		return expression.getType() instanceof BoolType;
	}

}
