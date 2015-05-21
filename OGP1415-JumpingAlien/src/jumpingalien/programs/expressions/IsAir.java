package jumpingalien.programs.expressions;


import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsAir extends CheckerExpression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsAir(Expression<ObjectType<?>> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public BoolType evaluate(Program program) {
		if(this.getStopProgram() || program.hasStopped()){
			program.stop();
			return null;
		}
		int x=((TileType) this.getExpression().evaluate(program)).getValue().get(0);
		int y=((TileType) this.getExpression().evaluate(program)).getValue().get(1);
		return new BoolType(program.getGameObject().getWorld().getTileValueAtTilePosition(x, y)==0);
	}

	@Override
	public boolean checkType(Expression<ObjectType<?>> expression) {
		return expression.getType() instanceof TileType;
	}

	

}
