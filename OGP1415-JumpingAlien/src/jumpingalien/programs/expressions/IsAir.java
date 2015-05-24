package jumpingalien.programs.expressions;


import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsAir<T extends ObjectType<?>> extends CheckerExpression<T> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsAir(Expression<T> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public BoolType evaluateLegalCase(Program program) {
		int [] tile = (int[]) this.getExpression().evaluateLegalCase(program).getValue();
		return new BoolType(program.getGameObject().getWorld().getTileValueAtTilePosition(tile[0], tile[1])==0);
	}

	@Override
	public boolean checkType(Expression<T> expression) {
		return expression.getType() instanceof TileType;
	}

	

}
