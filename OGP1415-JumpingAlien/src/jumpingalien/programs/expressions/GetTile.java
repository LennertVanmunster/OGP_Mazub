package jumpingalien.programs.expressions;

import java.util.*;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class GetTile extends BinaryOperation<TileType, DoubleType> {


	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public GetTile(Expression<DoubleType> x, Expression<DoubleType> y, SourceLocation sourceLocation) {
		super(x, y, sourceLocation);
	}


	@Override
	public Type<?> getType() {
		return new GameObjectType();
	}

	@Override
	public TileType evaluate(Program program) {
		if(this.getStopProgram()){
			program.stop();
		}
		int[] tileArray= program.getWorld().getTilePositionAtPixelLocation(((DoubleType) getExpressionLeft().evaluate(program)).getValue().intValue(), ((DoubleType) getExpressionRight().evaluate(program)).getValue().intValue());
		List<Integer> tile= new ArrayList<Integer>();
		tile.add(tileArray[0]);
		tile.add(tileArray[1]);
		return new TileType(tile);
	}

	@Override
	public boolean checkType(Expression<DoubleType> expression) {
		return expression.getType() instanceof DoubleType;
	}

}
