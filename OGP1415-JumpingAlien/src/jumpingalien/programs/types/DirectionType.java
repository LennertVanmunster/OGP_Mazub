package jumpingalien.programs.types;

import jumpingalien.model.Direction;
import jumpingalien.programs.expressions.*;

public class DirectionType extends Type{
	public DirectionType(){
		
	}
	
	public Expression getDefaultValue(){
		return new DirectionConstant(Direction.LEFT);
	}
	
}
