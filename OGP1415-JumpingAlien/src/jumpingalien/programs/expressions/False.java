package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class False extends BoolExpression {

	public False(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public False(){
		this(null);
	}
	
	@Override
	public BoolType evaluate(Program program){
		return new BoolType(false);
	}
	
	
}
