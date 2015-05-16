/**
 * 
 */
package jumpingalien.programs.expressions;

import java.util.List;

import jumpingalien.model.Direction;
import jumpingalien.model.GameObject;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class SearchObject extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public SearchObject(Expression direction, SourceLocation sourceLocation) {
		super(direction, sourceLocation);
	}


	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return new ObjectType();
	}


	@Override
	public Object evaluate(Program program) {
		Object object = null;
		Direction direction = (Direction) this.getExpression().evaluate(program);
		GameObject gameObject = program.getGameObject();
		int[] position = {(int) gameObject.getHorizontalLocation(), (int) gameObject.getVerticalLocation()};
		World world = program.getWorld();
		if(direction == Direction.RIGHT || direction == Direction.LEFT){
			while(gameObject.canHaveAsLocation(position[0], position[1])){
				object = gameObject.getGameObjectAtPixelPosition(position[0], position[1]);
				if(object != null){
					return object;
				}
				position[0] = position[0] + direction.getNumberForCalculations();
				try{
				object = world.getTilePositionAtPixelLocation(position[0], position[1]);
				}catch(IllegalArgumentException exc){
					object = null;
				}
			}
		}else if(direction == Direction.UP || direction == Direction.DOWN){
			while(gameObject.canHaveAsLocation(position[0], position[1])){
				object = gameObject.getGameObjectAtPixelPosition(position[0], position[1]);
				if(object != null){
					return object;
				}
				position[1] = position[1] + direction.getNumberForCalculations();
				try{
				object = world.getTilePositionAtPixelLocation(position[0], position[1]);
				}catch(IllegalArgumentException exc){
					object = null;
				}
			}
		}
		return object;
	}

}
