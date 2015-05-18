package jumpingalien.programs.types;

//import jumpingalien.part3.programs.IProgramFactory.Direction;

import jumpingalien.model.Orientation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;

public class DirectionType extends Type{
	public DirectionType(){
		
	}
	
	public Expression getDefaultValue(){
		return new DirectionConstant(Direction.LEFT);
	}

	@Override
	public Expression createExpression(Expression value, Program program) {
		return new DirectionConstant((Direction) Orientation.DUMMY.convertOrientation((Orientation) value.evaluate(program)));
	}
	
}