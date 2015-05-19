package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsTerrain extends CheckerExpression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsTerrain(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public Boolean evaluate(Program program) {
		int [] tile = (int[]) this.getExpression().evaluate(program);
		return program.getWorld().canHaveAsTilePosition(tile[0], tile[1]);
	}

}
