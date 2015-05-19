package jumpingalien.programs.expressions;

import jumpingalien.model.Orientation;
import jumpingalien.model.GameObject;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
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
		return new GameObjectType();
	}


	@Override
	public Object evaluate(Program program) {
		Object object = null;
		Orientation direction = (Orientation) this.getExpression().evaluate(program);
		GameObject gameObject = program.getGameObject();
		int[] position = {(int) gameObject.getHorizontalLocation(), (int) gameObject.getVerticalLocation()};
		World world = program.getWorld();
		if(direction == Orientation.RIGHT || direction == Orientation.LEFT){
			while(gameObject.canHaveAsLocation(position[0], position[1])){
				object = gameObject.getGameObjectAtPixelPosition(position[0], position[1]);
				if(object != null && object != gameObject){
					return object;
				}
				position[0] = position[0] + direction.getNumberForCalculations();
				try{
				object = world.getTilePositionAtPixelLocation(position[0], position[1]);
				}catch(IllegalArgumentException exc){
					object = null;
				}
			}
		}else if(direction == Orientation.UP || direction == Orientation.DOWN){
			while(gameObject.canHaveAsLocation(position[0], position[1])){
				object = gameObject.getGameObjectAtPixelPosition(position[0], position[1]);
				if(object != null && object != gameObject){
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
