package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Equals<T extends Type<?>> extends Comparison<T> {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public Equals(Expression<T> left, Expression<T> right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public BoolType evaluateLegalCase(Program program) {
		Type<?> left = this.getExpressionLeft().evaluateLegalCase(program);
		Type<?> right = this.getExpressionRight().evaluateLegalCase(program);
		if(!left.getClass().equals(right.getClass())){
			return new BoolType(false);
		}
		else if(left instanceof DoubleType){
			return new BoolType(jumpingalien.util.Util.fuzzyEquals(((DoubleType) left).getValue(),((DoubleType) right).getValue()));
		}
		else{
			return new BoolType(left.getValue() == right.getValue());
		}
	}

	@Override
	public boolean checkType(Expression<T> expression) {
		return true;
	}

}
