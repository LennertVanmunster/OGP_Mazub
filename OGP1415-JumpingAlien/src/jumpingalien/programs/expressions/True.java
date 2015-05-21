package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class True extends BoolExpression {
	
	public True(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public BoolType evaluateLegalCase(Program program){
		return new BoolType(true);
	}
}
