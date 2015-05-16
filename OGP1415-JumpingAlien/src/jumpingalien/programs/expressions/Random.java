/**
 * 
 */
package jumpingalien.programs.expressions;

import java.util.Random;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
 * @author Pieter
 *
 */
public class RandomDouble extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public RandomDouble(Expression maxvalue, SourceLocation sourceLocation) {
		super(maxvalue, sourceLocation);
		// TODO Auto-generated constructor stub
	}

	public Type getType(){
		return new DoubleType();
	}

	@Override
	public Object evaluate(Program program) {
		Random r = new Random();
		return (double)this.getExpression().evaluate(program) * r.nextDouble();
	}

}
