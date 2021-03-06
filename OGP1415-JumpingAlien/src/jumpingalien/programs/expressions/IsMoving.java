package jumpingalien.programs.expressions;

import jumpingalien.model.Orientation;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.DirectionType;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.ObjectType;
import jumpingalien.programs.types.TileType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsMoving<T extends ObjectType<?>> extends CheckerExpression<T> {



	/**
	 * @param expression
	 * @param sourceLocation
	 */

	public IsMoving(Expression<T> expression, Expression<DirectionType> direction, SourceLocation sourceLocation) {

		super(expression, sourceLocation);
		this.setDirection(direction);
	}

	/**
	 * @return the direction
	 */
	public Expression<DirectionType> getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	private void setDirection(Expression<DirectionType> direction) {
		if(!checkTypeDirection(direction)){
			setStopProgram(true);
		}
		this.direction = direction;
	}

	private Expression<DirectionType> direction;

	
	@Override
	public BoolType getType() {
		return new BoolType();
	}

	@Override
	public BoolType evaluateLegalCase(Program program) {
		ObjectType<?> object = (ObjectType<?>) this.getExpression().evaluateLegalCase(program);
		if (object instanceof TileType){
			return new BoolType(false);
		}
		GameObjectType gameObject = (GameObjectType) object;
		return new BoolType(gameObject.getValue().isMovingHorizontally() && gameObject.getValue().getDirection() == 
				Orientation.DUMMY.convertDirectionIProgramFactory(((DirectionType) this.getDirection().evaluateLegalCase(program)).getValue()));
				
	}
	
	@Override
	public boolean checkType(Expression<T> expression) {
		return expression.getType() instanceof GameObjectType;
	}
	
	public boolean checkTypeDirection(Expression<DirectionType> direction){
		return direction.getType() instanceof DirectionType;
	}


}
