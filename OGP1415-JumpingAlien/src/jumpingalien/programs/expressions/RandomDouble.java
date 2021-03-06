package jumpingalien.programs.expressions;

import java.util.Random;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class RandomDouble extends UnaryOperation<DoubleType, DoubleType> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public RandomDouble(Expression<DoubleType> maxvalue, SourceLocation sourceLocation) {
		super(maxvalue, sourceLocation);
	}

	@Override
	public DoubleType getType(){
		return new DoubleType();
	}

	@Override
	public DoubleType evaluateLegalCase(Program program) {
		Random r = new Random();
		return new DoubleType(((DoubleType)this.getExpression().evaluateLegalCase(program)).getValue() * r.nextDouble());
	}

	@Override
	public boolean checkType(Expression<DoubleType> expression) {
		return expression.getType() instanceof DoubleType;
	}

}
