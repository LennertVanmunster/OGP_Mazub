/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;
import jumpingalien.part3.programs.SourceLocation;

/**
 * @author Pieter
 *
 */
public class DirectionConstant extends Expression {

	public DirectionConstant(Direction value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setDirection(value);
	}
	
	public DirectionConstant(Direction value) {
		super(new SourceLocation(0,0));
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
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	private Direction direction;

	public Type getType(){
		return new DirectionType();
	}

	@Override
	public Object evaluate(Program program) {
		return this.getDirection();
	}

}
