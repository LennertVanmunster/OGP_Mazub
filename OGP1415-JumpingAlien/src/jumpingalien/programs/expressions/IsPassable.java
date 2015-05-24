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
public class IsPassable<T extends ObjectType<?>> extends CheckerExpression<T> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsPassable(Expression<T> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public BoolType evaluateLegalCase(Program program) {
		int x=((TileType) this.getExpression().evaluateLegalCase(program)).getValue()[0];
		int y=((TileType) this.getExpression().evaluateLegalCase(program)).getValue()[1];
		return new BoolType(program.getGameObject().getWorld().getTileValueAtTilePosition(x, y)!=1);
	}

	@Override
	public boolean checkType(Expression<T> expression) {
		return expression.getType() instanceof ObjectType<?>;
	}


}
