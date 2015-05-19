package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.model.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class GetHeigth extends GetDoubleValueOf {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public GetHeigth(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Double evaluate(Program program) {
		return (double)((GameObject)this.getExpression().evaluate(program)).getHeight();
	}

}
