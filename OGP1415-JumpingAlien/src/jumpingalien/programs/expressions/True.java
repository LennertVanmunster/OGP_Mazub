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
	
	public True(){
		this(null);
	}
	
	@Override
	public BoolType evaluate(Program program){
		if(this.getStopProgram() || program.hasStopped()){
			program.stop();
			return null;
		}
		return new BoolType(true);
	}
}
