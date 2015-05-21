package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.ObjectType;
import jumpingalien.programs.types.TileType;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsTerrain extends CheckerExpression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsTerrain(Expression<ObjectType<?>> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public BoolType evaluateLegalCase(Program program) {
		int [] tile = (int[]) this.getExpression().evaluateLegalCase(program).getValue();
		return new BoolType(program.getWorld().canHaveAsTilePosition(tile[0], tile[1]));
	}


	@Override
	public boolean checkType(Expression<ObjectType<?>> expression) {
		return expression.getType() instanceof ObjectType;
	}

}
