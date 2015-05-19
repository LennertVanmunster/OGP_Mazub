package jumpingalien.programs.expressions;

import jumpingalien.model.Plant;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsPlant extends CheckerExpression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsPlant(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Boolean evaluate(Program program) {
		return this.getExpression().evaluate(program) instanceof Plant;
	}

}
