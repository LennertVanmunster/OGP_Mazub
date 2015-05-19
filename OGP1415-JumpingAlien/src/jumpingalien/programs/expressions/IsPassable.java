package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsPassable extends CheckerExpression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsPassable(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public Boolean evaluate(Program program) {
		int [] tile = (int[]) this.getExpression().evaluate(program);
		return program.getWorld().getTileValueAtTilePosition(tile[0], tile[1]) != 1;
	}

}
