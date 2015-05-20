package jumpingalien.programs.types;

//import jumpingalien.part3.programs.IProgramFactory.Direction;

import jumpingalien.model.Orientation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;

public class DirectionType extends Type<Direction>{
	public DirectionType(Direction value){
		super(value);
	}
	
	public DirectionType(){
		this(Direction.LEFT);
	}
	
	public Direction getDefaultValue(){
		return Direction.LEFT;
	}

//	@Override
//	public Expression createExpression(Expression value, Program program) {
//		return new DirectionConstant((Direction) Orientation.DUMMY.convertOrientation((Orientation) value.evaluate(program)));
//	}

	@Override
	public void setValue(Direction value) {
		this.value=value;
	}
	
}