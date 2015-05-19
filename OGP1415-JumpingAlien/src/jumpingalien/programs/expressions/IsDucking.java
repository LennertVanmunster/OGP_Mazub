package jumpingalien.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsDucking extends CheckerExpression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsDucking(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public Boolean evaluate(Program program) {
		GameObject gameObject = ((GameObject)this.getExpression().evaluate(program));
		return gameObject.isDucking();
	}

}
