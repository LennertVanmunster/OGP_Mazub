package jumpingalien.programs.expressions;

import jumpingalien.model.Direction;
import jumpingalien.programs.types.*;

public class DirectionConstant extends Expression {
	public DirectionConstant(Direction direction){
		setDirection(direction);
	}
	
	public void setDirection(Direction direction){
		this.direction=direction;
	}
	
	public Direction getDirection(){
		return this.direction;
	}
	
	private Direction direction;
	
	public Type getType(){
		return new DirectionType();
	}
}
