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
public class IsWater<T extends ObjectType<?>> extends CheckerExpression<T> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsWater(Expression<T> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public BoolType evaluateLegalCase(Program program) {
		int [] tile = (int[]) this.getExpression().evaluateLegalCase(program).getValue();
		return new BoolType(program.getGameObject().getWorld().getTileValueAtTilePosition(tile[0], tile[1])==2);
	}

	@Override
	public boolean checkType(Expression<T> expression) {
		return expression.getType() instanceof TileType;
	}

}
