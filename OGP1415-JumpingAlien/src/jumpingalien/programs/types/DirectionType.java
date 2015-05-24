package jumpingalien.programs.types;

//import jumpingalien.part3.programs.IProgramFactory.Direction;

import jumpingalien.part3.programs.IProgramFactory.Direction;

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

	@Override
	public void setValue(Direction value) {
		this.value=value;
	}
	
}