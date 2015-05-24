package jumpingalien.programs.expressions;


import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class DirectionConstant extends Expression<DirectionType> {

	public DirectionConstant(Direction value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setDirection(value);
	}
	
	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	private void setDirection(Direction direction) {
		this.direction = direction;
	}

	private Direction direction;

	@Override
	public DirectionType getType(){
		return new DirectionType();
	}

	@Override
	public DirectionType evaluateLegalCase(Program program) {
		return new DirectionType(this.getDirection());
	}

}
