package jumpingalien.programs.types;

//import jumpingalien.part3.programs.IProgramFactory.Direction;

import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.programs.expressions.*;

public class DirectionType extends Type{
	public DirectionType(){
		
	}
	
	public Expression getDefaultValue(){
		return new DirectionConstant(Direction.LEFT);
	}
	
}