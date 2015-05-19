package jumpingalien.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class GetWidth extends GetDoubleValueOf {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public GetWidth(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Double evaluate(Program program) {
		return (double)((GameObject)this.getExpression().evaluate(program)).getWidth();
	}

}
