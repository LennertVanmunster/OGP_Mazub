package jumpingalien.programs.expressions;

import java.util.Random;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class RandomDouble extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public RandomDouble(Expression maxvalue, SourceLocation sourceLocation) {
		super(maxvalue, sourceLocation);
	}

	@Override
	public Type getType(){
		return new DoubleType();
	}

	@Override
	public Double evaluate(Program program) {
		Random r = new Random();
		return (double)this.getExpression().evaluate(program) * r.nextDouble();
	}

}
