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
		ObjectType<?> object = (ObjectType<?>) this.getExpression().evaluateLegalCase(program);
		if (object instanceof GameObjectType){
			return new BoolType(false);
		}
		TileType tile = (TileType) object;
		return new BoolType(program.getGameObject().getWorld().getTileValueAtTilePosition(tile.getValue()[0], tile.getValue()[1])==0);
	}

	@Override
	public boolean checkType(Expression<T> expression) {
		return expression.getType() instanceof TileType;
	}

	

}
