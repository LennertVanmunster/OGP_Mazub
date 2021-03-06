package jumpingalien.programs.expressions;


import jumpingalien.model.Orientation;
import jumpingalien.model.GameObject;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class SearchObject extends UnaryOperation<ObjectType<?>, DirectionType> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public SearchObject(Expression<DirectionType> direction, SourceLocation sourceLocation) {
		super(direction, sourceLocation);
	}


	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public GameObjectType getType() {
		return new GameObjectType();
	}


	@Override
	public ObjectType<?> evaluateLegalCase(Program program) {
		ObjectType<?> object = null;
		Orientation direction = Orientation.DUMMY.convertDirectionIProgramFactory(((DirectionType) this.getExpression().evaluateLegalCase(program)).getValue());
		GameObject gameObject = program.getGameObject();
		int[] position = {(int) gameObject.getHorizontalLocation(), (int) gameObject.getVerticalLocation()};
		World world = program.getWorld();
		if(direction == Orientation.RIGHT || direction == Orientation.LEFT){
			while(gameObject.canHaveAsLocation(position[0], position[1])){
				object = new GameObjectType(gameObject.getGameObjectAtPixelPosition(position[0], position[1]));
				if(object.getValue() != null && object.getValue() != gameObject){
					return object;
				}
				position[0] = position[0] + direction.getNumberForCalculations();
				try{
					int[] tileArray= world.getTilePositionAtPixelLocation(position[0], position[1]);
					object = new TileType(tileArray);
				}catch(IllegalArgumentException exc){
					object = new GameObjectType(null);
				}
			}
		}else if(direction == Orientation.UP || direction == Orientation.DOWN){
			while(gameObject.canHaveAsLocation(position[0], position[1])){
				object = new GameObjectType(gameObject.getGameObjectAtPixelPosition(position[0], position[1]));
				if(object.getValue() != null && object.getValue() != gameObject){
					return object;
				}
				position[1] = position[1] + direction.getNumberForCalculations();
				try{
					int[] tileArray= world.getTilePositionAtPixelLocation(position[0], position[1]);
					object = new TileType(tileArray);
				}catch(IllegalArgumentException exc){
					object = new GameObjectType(null);
				}
			}
		}
		return object;
	}


	@Override
	public boolean checkType(Expression<DirectionType> expression) {
		return expression.getType() instanceof DirectionType;
	}

}
